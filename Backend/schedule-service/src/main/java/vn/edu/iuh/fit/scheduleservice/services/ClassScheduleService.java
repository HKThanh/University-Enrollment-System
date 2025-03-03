package vn.edu.iuh.fit.scheduleservice.services;

import vn.edu.iuh.fit.scheduleservice.dtos.EnrollGroup;
import vn.edu.iuh.fit.scheduleservice.dtos.QueryClassSchedule;
import vn.edu.iuh.fit.scheduleservice.models.ClassSchedule;
import vn.edu.iuh.fit.scheduleservice.models.Schedule;
import vn.edu.iuh.fit.scheduleservice.repositories.ClassScheduleRepository;

import java.util.List;
import java.util.Map;

public interface ClassScheduleService {
    List<ClassSchedule> getAllClassSchedule();
    List<ClassSchedule> getClassScheduleByStudentId(String studentId);
    Map<String, ClassSchedule> getClassScheduleByClassId(List<String> classIds);
    List<QueryClassSchedule> getEachScheduleByClassIds(List<EnrollGroup> classIds);


}
