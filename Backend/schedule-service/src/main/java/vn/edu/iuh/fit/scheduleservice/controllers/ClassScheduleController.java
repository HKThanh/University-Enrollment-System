package vn.edu.iuh.fit.scheduleservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.scheduleservice.dtos.QueryClassSchedule;
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

    private boolean isConflict(QueryClassSchedule existingSchedule, QueryClassSchedule newSchedule) {
        // Check if the schedules are on the same day of the week
        if (existingSchedule.schedule().getDayOfWeek() != newSchedule.schedule().getDayOfWeek()) {
            return false;
        }

        // Split the time slots into start and end times
        String[] existingTime = existingSchedule.schedule().getTimeSlot().split("-");
        String[] newTime = newSchedule.schedule().getTimeSlot().split("-");

        // Check if the schedules overlap in time
        if (Integer.parseInt(existingTime[0]) < Integer.parseInt(newTime[1]) &&
                Integer.parseInt(existingTime[1]) > Integer.parseInt(newTime[0])) {
            // If the schedules overlap in time, check if they also overlap in date
            return existingSchedule.schedule().getStartDate().before(newSchedule.schedule().getEndDate()) &&
                    existingSchedule.schedule().getEndDate().after(newSchedule.schedule().getStartDate());
        }

        return false;
    }

}
