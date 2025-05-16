package vn.edu.iuh.fit.enrollservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.enrollservice.models.PaymentStatus;
import vn.edu.iuh.fit.enrollservice.models.Enrollment;
import vn.edu.iuh.fit.enrollservice.models.EnrollmentClassId;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentClassId> {
    Optional<Enrollment> findByStudentIdAndRegistryClass(String studentId, String classId);

//    @Procedure("register_class")
//    int registerClass(String p_student_id, String p_class_id, int p_group_id);
    @Query(nativeQuery = true, value = "SELECT register_class(:p_student_id, :p_class_id, :p_group_id)")
    int registerClass(@Param("p_student_id") String p_student_id,
                      @Param("p_class_id") String p_class_id,
                      @Param("p_group_id") int p_group_id);

    // @Procedure(procedureName = "change_class")
    // int changeClass(String p_student_id, String p_old_class_id, String p_new_class_id, int p_group_id);

    @Query(nativeQuery = true, value = "SELECT change_class(:p_student_id, :p_old_class_id, :p_new_class_id, :p_group_id)")
    int changeClass(@Param("p_student_id") String p_student_id, 
                    @Param("p_old_class_id") String p_old_class_id, 
                    @Param("p_new_class_id") String p_new_class_id, 
                    @Param("p_group_id") int p_group_id);

    @Query("SELECT e FROM Enrollment e WHERE e.studentId = :studentId AND e.semester = :semester AND e.year = :year")
    List<Enrollment> findEnrollmentsIncludingSemesterAndYear(String studentId, int semester, int year);

    @Query("SELECT e FROM Enrollment e WHERE e.studentId = :studentId AND NOT (e.semester = :semester AND e.year = :year)")
    List<Enrollment> findEnrollmentsExcludingSemesterAndYear(String studentId, int semester, int year);

    List<Enrollment> findEnrollmentByStudentIdAndRegistryClassIn(String studentId, List<String> classIds);
}
