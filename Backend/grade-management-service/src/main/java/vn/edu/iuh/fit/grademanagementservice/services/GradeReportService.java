package vn.edu.iuh.fit.grademanagementservice.services;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.grademanagementservice.models.GradeReport;

import java.util.List;

public interface GradeReportService {
    List<GradeReport> findByStudentId(String studentId);
    GradeReport findByStudetnIdAndCourseId(String studentId, String courseId);
}
