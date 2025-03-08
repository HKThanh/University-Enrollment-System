package vn.edu.iuh.fit.grademanagementservice.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.grademanagementservice.models.SemesterSummary;

import java.util.List;

public interface SemesterSummaryService {
    List<SemesterSummary> getSemesterSummaryByStudentId(String studentId);

    SemesterSummary getSemesterSummaryByStudentIdAndSemesterAndYear(String studentId, int semester, int year);
}
