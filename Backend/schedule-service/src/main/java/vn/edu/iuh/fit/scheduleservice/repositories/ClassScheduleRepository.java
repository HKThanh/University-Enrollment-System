package vn.edu.iuh.fit.scheduleservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.scheduleservice.models.ClassSchedule;
import vn.edu.iuh.fit.scheduleservice.models.StudentSchedule;

import java.util.List;

@Repository
public interface ClassScheduleRepository extends MongoRepository<ClassSchedule, String> {
    List<ClassSchedule> findByClassIdIn(List<String> classIds);
}

