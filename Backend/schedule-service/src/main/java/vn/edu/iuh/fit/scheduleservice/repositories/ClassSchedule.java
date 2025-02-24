package vn.edu.iuh.fit.scheduleservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassSchedule extends MongoRepository<ClassSchedule, String> {
}

