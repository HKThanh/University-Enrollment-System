package vn.edu.iuh.fit.scheduleservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.scheduleservice.models.StudentSchedule;

import java.util.List;

@Repository
public interface StudentScheduleRepository extends MongoRepository<StudentSchedule, String> {
    List<StudentSchedule> findByStudentId(String studentId);

    void deleteByStudentIdAndClassId(String studentId, String classId);
}
