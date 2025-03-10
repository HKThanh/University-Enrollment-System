package vn.edu.iuh.fit.grademanagementservice.services.impl;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.grademanagementservice.models.SemesterSummary;
import vn.edu.iuh.fit.grademanagementservice.repositories.SemesterSummaryRepository;
import vn.edu.iuh.fit.grademanagementservice.services.SemesterSummaryService;

import java.util.List;

@Service
public class SemesterSummaryServiceImpl implements SemesterSummaryService {
    SemesterSummaryRepository semesterSummaryRepository;
    private final MongoTemplate mongoTemplate;

    public SemesterSummaryServiceImpl(SemesterSummaryRepository semesterSummaryRepository, MongoTemplate mongoTemplate) {
        this.semesterSummaryRepository = semesterSummaryRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<SemesterSummary> getSemesterSummaryByStudentId(String studentId) {
        return semesterSummaryRepository.findByStudentId(studentId);
    }

    @Override
    public SemesterSummary getSemesterSummaryByStudentIdAndSemesterAndYear(String studentId, int semester, int year) {
        Query query = new Query();
        query.addCriteria(Criteria.where("studentId").is(studentId)
                .and("semester").is(semester)
                .and("year").is(year));
        return mongoTemplate.findOne(query, SemesterSummary.class);
    }
}
