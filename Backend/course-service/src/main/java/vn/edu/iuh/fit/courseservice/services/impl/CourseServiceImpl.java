package vn.edu.iuh.fit.courseservice.services.impl;


import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.courseservice.dtos.CourseDTO;
import vn.edu.iuh.fit.courseservice.dtos.ListCourseResponse;
import vn.edu.iuh.fit.courseservice.models.Course;
import vn.edu.iuh.fit.courseservice.services.CourseService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.ArrayOperators.Filter.filter;
import static org.springframework.data.mongodb.core.aggregation.VariableOperators.mapItemsOf;

@Service
public class CourseServiceImpl implements CourseService {
    private static final String COURSE_ON_MAJOR = "course_on_major";
    private static final String MAJOR_ID = "major_id";
    private static final String ELECTIVE_GROUP = "elective_group";
    private static final String ACADEMIC_YEAR = "academic_year";
    private static final String PREREQUISITES = "prerequisites";
    private static final String COURSE = "course";
    private static final String ID = "_id";
    private static final String COURSE_ID = "course_id";
    private static final String CREDIT = "credit";
    private static final String NAME = "name";
    private static final String THEORY_CREDIT = "theory_credit";
    private static final String PRACTICAL_CREDIT = "practical_credit";
    private static final String SEMESTER = "semester";
    private static final String TYPE = "type";

    private final MongoTemplate mongoTemplate;

    public CourseServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Map<Integer, List<CourseDTO>> listAllCourseByMajorAndYear(int majorId, int year) {
        return Map.of();
    }

    @Override
    public List<ListCourseResponse> getCoursesByIds(int majorId, List<String> courseIds) {
        return List.of();
    }

}
