package vn.edu.iuh.fit.authservices.dtos;


public record AuthResponse(StudentDTO student,String accessToken, String refreshToken) {
}
