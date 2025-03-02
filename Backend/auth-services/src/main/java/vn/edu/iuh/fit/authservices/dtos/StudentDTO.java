package vn.edu.iuh.fit.authservices.dtos;


public record StudentDTO(
        String id,
        String name,
        int majorId,
        String majorName,
        int year,
        int facultyId,
        String facultyName
) {
}