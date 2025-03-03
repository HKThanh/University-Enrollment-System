package vn.edu.iuh.fit.facultyservice.dtos;

public record StudentDTO(
        String id,
        String name,
        int majorId,
        String majorName,
        int year,
        int facultyId,
        String facultyName,
        String email
) {
}
