package vn.edu.iuh.fit.authservices.services;

import vn.edu.iuh.fit.authservices.models.Student;

import java.util.Optional;

public interface AuthService {
    Optional<Student> getStudentById(String id);
}
