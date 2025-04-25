package vn.edu.iuh.fit.grademanagementservice.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.grademanagementservice.dtos.StatisticResponse;
import vn.edu.iuh.fit.grademanagementservice.models.SemesterSummary;

import java.util.List;

public interface SemesterSummaryService {
    List<SemesterSummary> getSemesterSummaryByStudentId(String studentId);

    SemesterSummary getSemesterSummaryByStudentIdAndSemesterAndYear(String studentId, int semester, int year);

    SemesterSummary getSemesterSummary(String studentId, int semester, int year);

    List<SemesterSummary> getSemesterSummaries(String studentId);

    StatisticResponse getStatistics(String studentId, int semester, int year);

    int estimateScholarship(int majorId, int semester, int year, float gpa);
}
