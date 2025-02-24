package vn.edu.iuh.fit.scheduleservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.scheduleservice.dtos.ResponseWrapper;
import vn.edu.iuh.fit.scheduleservice.models.ClassSchedule;
import vn.edu.iuh.fit.scheduleservice.services.ClassScheduleService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schedules")
public class ClassScheduleController {
    private final ClassScheduleService classScheduleService;

    public ClassScheduleController(ClassScheduleService classScheduleService) {
        this.classScheduleService = classScheduleService;
    }

    @GetMapping("")
    public List<ClassSchedule> getAllSchedules() {
        return classScheduleService.getAllClassSchedule();
    }

    @GetMapping("/classes")
    public Map<String, ClassSchedule> getSchedules(@RequestParam List<String> class_ids) {
        return classScheduleService.getClassScheduleByClassId(class_ids);
    }

    @GetMapping("/classes/{id}")
    public ResponseEntity<?> getScheduleByClassId(@PathVariable("id") String classId) {
        return ResponseEntity.ok(
                new ResponseWrapper(
                        "Lịch học của lớp " + classId,
                        classScheduleService.getClassScheduleByClassId(List.of(classId)),
                        HttpStatus.OK.value()
                )
        );
    }

}
