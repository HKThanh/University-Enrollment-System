package vn.edu.iuh.fit.courseservice.dtos;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
public class CourseDTO {
    @Id
    private String id;
    private String name;
    private int credit;
    @Field("theory_credit")
    private int theoryCredit;
    @Field("practical_credit")
    private int practicalCredit;
    private int type;
    private int semester;
    @Field("elective_group")
    private int electiveGroup;
    private List<Prerequisite> prerequisites;
}