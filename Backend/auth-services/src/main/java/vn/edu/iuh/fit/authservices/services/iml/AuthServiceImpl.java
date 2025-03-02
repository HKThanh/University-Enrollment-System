package vn.edu.iuh.fit.authservices.services.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.authservices.models.Student;
import vn.edu.iuh.fit.authservices.repositories.StudentRepository;
import vn.edu.iuh.fit.authservices.services.AuthService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
   private final StudentRepository studentRepository;
   @Autowired
    public AuthServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }
}
