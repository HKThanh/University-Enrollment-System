package vn.edu.iuh.fit.grademanagementservice.services.impl;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.grademanagementservice.models.GradeReport;
import vn.edu.iuh.fit.grademanagementservice.repositories.GradeReportRepository;
import vn.edu.iuh.fit.grademanagementservice.services.GradeReportService;

import java.util.List;

@Service
public class GradeReportServiceImpl implements GradeReportService {
    GradeReportRepository gradeReportRepository;

    public GradeReportServiceImpl(GradeReportRepository gradeReportRepository) {
        this.gradeReportRepository = gradeReportRepository;
    }

    @Override
    public List<GradeReport> findByStudentId(String studentId) {
        return gradeReportRepository.findByStudentId(studentId);
    }
}
