package vn.edu.iuh.fit.grademanagementservice.services.impl;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.grademanagementservice.models.GradeReport;
import vn.edu.iuh.fit.grademanagementservice.repositories.GradeReportRepository;
import vn.edu.iuh.fit.grademanagementservice.services.GradeReportService;

import java.util.List;

@Service
public class GradeReportServiceImpl implements GradeReportService {
    GradeReportRepository gradeReportRepository;
    private final MongoTemplate mongoTemplate;

    public GradeReportServiceImpl(GradeReportRepository gradeReportRepository, MongoTemplate mongoTemplate) {
        this.gradeReportRepository = gradeReportRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<GradeReport> findByStudentId(String studentId) {
        return gradeReportRepository.findByStudentId(studentId);
    }

    @Override
    public GradeReport findByStudetnIdAndCourseId(String studentId, String courseId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("studentId").is(studentId).and("courseId").is(courseId));
        return mongoTemplate.findOne(query, GradeReport.class);
    }

    @Override
    public GradeReport getGradeReport(String studentId, String courseId) {
        return gradeReportRepository.findByStudentIdAndClassId(studentId, courseId);
    }
}
