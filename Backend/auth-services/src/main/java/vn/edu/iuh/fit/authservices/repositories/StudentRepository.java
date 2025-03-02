package vn.edu.iuh.fit.authservices.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.authservices.models.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
