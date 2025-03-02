package vn.edu.iuh.fit.facultyservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "faculties")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "faculty")
    @JsonIgnore
    private List<Major> majors = new ArrayList<>();
}
