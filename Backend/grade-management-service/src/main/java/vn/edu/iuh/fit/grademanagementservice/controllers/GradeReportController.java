package vn.edu.iuh.fit.grademanagementservice.controllers;

import jakarta.ws.rs.core.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.grademanagementservice.dtos.ResponseWrapper;
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

    @GetMapping("/find")
    public ResponseEntity<?> getGradeReportByStudentIdAndCourseId(@RequestHeader("id") String studentId, @RequestParam String courseId) {
        return ResponseEntity.ok(gradeReportService.findByStudetnIdAndCourseId(studentId, courseId));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getGradeReport(@RequestHeader("id") String studentId, @RequestParam("course_id") String courseId) {
        return ResponseEntity.ok(new ResponseWrapper("Success", gradeReportService.getGradeReport(studentId, courseId), 200));
    }
}
