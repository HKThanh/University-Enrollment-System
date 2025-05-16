import axios from "axios";
import apiURL from "../config/config";
import { getAccessToken, getUser } from "../utils/auth";
import CryptoJS from "crypto-js";

export default axios.create({
    baseURL: apiURL,
});
export const axiosPrivate = axios.create({
    baseURL: apiURL,
    headers: {
        "Content-Type": "application/json",
    },
});

axiosPrivate.interceptors.request.use(
    (config) => {
        const token = getAccessToken();
        if (token) {
            config.headers["Authorization"] = `Bearer ${token}`;
        
            // Get majorId from the encrypted user info
            const encryptedUser = getUser();
            
            if (encryptedUser) {
                try {
                    // Decrypt the user data
                    const bytes = CryptoJS.AES.decrypt(encryptedUser, token);
                    const userData = JSON.parse(bytes.toString(CryptoJS.enc.Utf8));

                    // Add major_id header if it exists in user data
                    if (userData && userData.majorId) {
                        config.headers["major_id"] = userData.majorId.toString();
                        config.headers["academic_year"] = userData.year.toString();
                        config.headers["id"] = userData.id.toString();
                    }
                } catch (error) {
                    console.error("Error decrypting user data:", error);
                }
            }
        }

        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);
