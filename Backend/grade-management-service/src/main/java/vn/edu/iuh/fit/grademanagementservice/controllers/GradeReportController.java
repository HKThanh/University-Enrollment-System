package vn.edu.iuh.fit.grademanagementservice.controllers;

import jakarta.ws.rs.core.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.grademanagementservice.services.GradeReportService;

@RestController
@RequestMapping("/grade-report")
public class GradeReportController {
    private final GradeReportService gradeReportService;

    public GradeReportController(GradeReportService gradeReportService) {
        this.gradeReportService = gradeReportService;
    }

    @GetMapping
    public ResponseEntity<?> getGradeReportByStudentId(@RequestParam String studentId) {
        return ResponseEntity.ok(gradeReportService.findByStudentId(studentId));
    }
}
