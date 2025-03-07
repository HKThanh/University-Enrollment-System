package vn.edu.iuh.fit.grademanagementservice.services.impl;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.grademanagementservice.models.SemesterSummary;
import vn.edu.iuh.fit.grademanagementservice.repositories.SemesterSummaryRepository;
import vn.edu.iuh.fit.grademanagementservice.services.SemesterSummaryService;

import java.util.List;

@Service
public class SemesterSummaryServiceImpl implements SemesterSummaryService {
    SemesterSummaryRepository semesterSummaryRepository;

    public SemesterSummaryServiceImpl(SemesterSummaryRepository semesterSummaryRepository) {
        this.semesterSummaryRepository = semesterSummaryRepository;
    }

    @Override
    public List<SemesterSummary> getSemesterSummaryByStudentId(String studentId) {
        return semesterSummaryRepository.findByStudentId(studentId);
    }
}
