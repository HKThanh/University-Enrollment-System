package vn.edu.iuh.fit.grademanagementservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.grademanagementservice.services.SemesterSummaryService;

@RestController
@RequestMapping("/semester-summary")
public class SemesterSummaryController {
    private final SemesterSummaryService semesterSummaryService;

    public SemesterSummaryController(SemesterSummaryService semesterSummaryService) {
        this.semesterSummaryService = semesterSummaryService;
    }

    @GetMapping
    public ResponseEntity<?> getSemesterSummaryByStudentId(@RequestParam String studentId) {
        return ResponseEntity.ok(semesterSummaryService.getSemesterSummaryByStudentId(studentId));
    }
}
