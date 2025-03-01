package vn.edu.iuh.fit.courseservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.edu.iuh.fit.courseservice.models.Course;

public interface CourseRepostory extends MongoRepository<Course, String> {
}
