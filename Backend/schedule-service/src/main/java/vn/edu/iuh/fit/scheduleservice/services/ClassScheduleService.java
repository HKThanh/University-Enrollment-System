package vn.edu.iuh.fit.scheduleservice.services;

import vn.edu.iuh.fit.scheduleservice.dtos.EnrollGroup;
import vn.edu.iuh.fit.scheduleservice.dtos.QueryClassSchedule;
import vn.edu.iuh.fit.scheduleservice.models.ClassSchedule;
import vn.edu.iuh.fit.scheduleservice.models.StudentSchedule;
import java.util.List;
import java.util.Map;

public interface ClassScheduleService {
    List<ClassSchedule> getAllClassSchedule();
    List<ClassSchedule> getClassScheduleByStudentId(String studentId);
    Map<String, ClassSchedule> getClassScheduleByClassId(List<String> classIds);
    List<QueryClassSchedule> getEachScheduleByClassIds(List<EnrollGroup> classIds);
    StudentSchedule registrySchedule(String studentId, String courseId, int group);
    void cancelSchedule(String studentId, String classId);
    void changeSchedule(String newClassId, String oldClassId, String studentId);
}
