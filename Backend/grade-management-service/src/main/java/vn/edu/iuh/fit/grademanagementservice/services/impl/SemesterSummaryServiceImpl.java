package vn.edu.iuh.fit.grademanagementservice.services.impl;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.grademanagementservice.dtos.StatisticResponse;
import vn.edu.iuh.fit.grademanagementservice.models.SemesterSummary;
import vn.edu.iuh.fit.grademanagementservice.models.GradeReport;
import vn.edu.iuh.fit.grademanagementservice.repositories.SemesterSummaryRepository;
import vn.edu.iuh.fit.grademanagementservice.services.SemesterSummaryService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public SemesterSummary getSemesterSummary(String studentId, int semester, int year) {
        return semesterSummaryRepository.findByStudentIdAndSemesterAndYear(studentId, semester, year);
    }

    @Override
    public List<SemesterSummary> getSemesterSummaries(String studentId) {
        return semesterSummaryRepository.findByStudentId(studentId);
    }

    @Override
    public StatisticResponse getStatistics(String studentId, int semester, int year) {
        List<Float> grades = new ArrayList<>();
        List<Float> averages = new ArrayList<>();
        List<String> subjects = new ArrayList<>();

        SemesterSummary semesterSummary = semesterSummaryRepository.findByStudentIdAndSemesterAndYear(studentId, semester, year);
        List<String> classIds = semesterSummary.getGradeReports().stream().map(GradeReport::getClassId).distinct().collect(Collectors.toList());
        List<Document> result = semesterSummaryRepository.findClassesAverageOverall(classIds);
        result.forEach(document -> {
            GradeReport existGradeReport = semesterSummary.getGradeReports().stream().filter(
                    gradeReport -> gradeReport.getClassId().equals(document.getString("_id"))
            ).findFirst().get();

            subjects.add(document.getString("name"));
            averages.add(document.getDouble("average").floatValue());
            grades.add(existGradeReport.getOverallScore());
        });
        return new StatisticResponse(subjects, grades, averages);
    }


    @Override
    public int estimateScholarship(int majorId, int semester, int year, float gpa) {
        if (semester == 1) {
            semester = 2;
            year -= 1;
        } else {
            semester--;
        }
        return semesterSummaryRepository.calculateScholarship(majorId, semester, year, gpa);
    }

}
