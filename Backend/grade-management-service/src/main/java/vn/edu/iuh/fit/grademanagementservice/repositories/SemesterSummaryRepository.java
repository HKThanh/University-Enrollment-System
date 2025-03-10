package vn.edu.iuh.fit.grademanagementservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.grademanagementservice.models.SemesterSummary;

import java.util.List;

@Repository
public interface SemesterSummaryRepository extends MongoRepository<SemesterSummary, String> {
    List<SemesterSummary> findByStudentId(String studentId);
}
