package vn.edu.iuh.fit.authservices.dtos;

public record LogoutRequest(String accessToken, String refreshToken) {
}
