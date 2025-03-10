package vn.edu.iuh.fit.grademanagementservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.grademanagementservice.models.GradeReport;

import java.util.List;

@Repository
public interface GradeReportRepository extends MongoRepository<GradeReport, String> {
    List<GradeReport> findByStudentId(String studentId);
}
