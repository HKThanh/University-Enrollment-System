package vn.edu.iuh.fit.scheduleservice.services.impl;

import com.mongodb.client.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.scheduleservice.dtos.EnrollGroup;
import vn.edu.iuh.fit.scheduleservice.dtos.QueryClassSchedule;
import vn.edu.iuh.fit.scheduleservice.models.ClassSchedule;
import vn.edu.iuh.fit.scheduleservice.models.ClassType;
import vn.edu.iuh.fit.scheduleservice.models.Schedule;
import vn.edu.iuh.fit.scheduleservice.models.StudentSchedule;
import vn.edu.iuh.fit.scheduleservice.repositories.ClassScheduleRepository;
import vn.edu.iuh.fit.scheduleservice.repositories.StudentScheduleRepository;
import vn.edu.iuh.fit.scheduleservice.services.ClassScheduleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ClassScheduleServiceImpl implements ClassScheduleService {
    private final ClassScheduleRepository classScheduleRepository;
    private final StudentScheduleRepository studentScheduleRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ClassScheduleServiceImpl(ClassScheduleRepository classScheduleRepository, StudentScheduleRepository studentScheduleRepository, MongoTemplate mongoTemplate) {
        this.classScheduleRepository = classScheduleRepository;
        this.studentScheduleRepository = studentScheduleRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<ClassSchedule> getAllClassSchedule() {
        return classScheduleRepository.findAll();
    }

    @Override
    public List<ClassSchedule> getClassScheduleByStudentId(String studentId) {
        List<StudentSchedule> studentSchedules = studentScheduleRepository.findByStudentId(studentId);

        List<String> classIds = studentSchedules.stream().map(StudentSchedule::getClassId).toList();

        return classScheduleRepository.findByClassIdIn(classIds);
    }

    @Override
    public Map<String, ClassSchedule> getClassScheduleByClassId(List<String> classId) {
        MatchOperation matchClass = Aggregation.match(new Criteria("_id").in(classId));

        Aggregation aggregation = Aggregation.newAggregation(
                matchClass
        );
        List<ClassSchedule> results = mongoTemplate.aggregate(aggregation, "classSchedule", ClassSchedule.class).getMappedResults();

        Map<String, ClassSchedule> resultMap = results.stream()
                .collect(Collectors.toMap(ClassSchedule::getClassId, Function.identity()));

        return resultMap;
    }

    @Override
    public List<QueryClassSchedule> getEachScheduleByClassIds(List<EnrollGroup> enrollGroups) {
        MatchOperation matchClass = Aggregation.match(new Criteria("_id").in(enrollGroups.stream().map(EnrollGroup::classId).collect(Collectors.toList())));
        UnwindOperation unwindSchedules = Aggregation.unwind("schedules");

        Map<String, Integer> groupMap = enrollGroups.stream().collect(Collectors.toMap(EnrollGroup::classId, EnrollGroup::group));

        Aggregation aggregation = Aggregation.newAggregation(
                matchClass,
                unwindSchedules,
                Aggregation.project()
                        .and("schedules").as("schedule")
                        .and("_id").as("classId")
                        .and("courseId").as("courseId")
                        .and("courseName").as("courseName"),
                Aggregation.match(
                        new Criteria().orOperator(
                                Criteria.where("schedule.classType").is(ClassType.THEORY),
                                Criteria.where("schedule.classType").is(ClassType.PRACTICE)
                        )
                ),
                Aggregation.project()
                        .and("_id").as("classId")
                        .and("courseId").as("courseId")
                        .and("courseName").as("courseName")
                        .and("schedule").as("schedules")
        );

        List<QueryClassSchedule> classSchedules = mongoTemplate.aggregate(aggregation, "classSchedule", QueryClassSchedule.class).getMappedResults();
        List<QueryClassSchedule> filteredSchedules = new ArrayList<>();
        for (QueryClassSchedule schedule : classSchedules) {
            if (schedule.schedule().getClassType().equals(ClassType.THEORY) || groupMap.get(schedule.classId()) == schedule.schedule().getGroup()) {
                filteredSchedules.add(schedule);
            }
        }
        return filteredSchedules;
    }
}
