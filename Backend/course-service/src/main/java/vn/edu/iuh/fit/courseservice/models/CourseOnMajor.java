package vn.edu.iuh.fit.courseservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseOnMajor {
    @Field("major_id")
    private int majorId;
    @Field("elective_group")
    private int electiveGroup;
    private int semester;
    private int type;
    @Field("academic_year")
    private int academicYear;
}
