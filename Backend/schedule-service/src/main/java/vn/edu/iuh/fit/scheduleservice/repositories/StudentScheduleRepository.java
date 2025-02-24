package vn.edu.iuh.fit.scheduleservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.scheduleservice.models.StudentSchedule;

@Repository
public interface StudentScheduleRepository extends MongoRepository<StudentSchedule, String> {
}
