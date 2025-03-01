package vn.edu.iuh.fit.courseservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.courseservice.dtos.CourseDTO;
import vn.edu.iuh.fit.courseservice.dtos.ListCourseResponse;
import vn.edu.iuh.fit.courseservice.models.Course;
import vn.edu.iuh.fit.courseservice.services.CourseService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getTop10Courses() {
        return ResponseEntity.ok(courseService.getTop10Courses());
    }

    @GetMapping("/by-ids")
    public List<ListCourseResponse> getCoursesByIds(@RequestHeader("major_id") int majorId, @RequestParam List<String> courseIds) {
        return courseService.getCoursesByIds(majorId, courseIds);
    }

    public List<CourseDTO> filterCourseByType(List<CourseDTO> courses, boolean isMandatory) {
        if (isMandatory) {
            return courses.stream().filter(course -> course.getType() == 1).collect(Collectors.toList());
        } else {
            return courses.stream().filter(course -> course.getType() == 0).collect(Collectors.toList());
        }
    }
}
