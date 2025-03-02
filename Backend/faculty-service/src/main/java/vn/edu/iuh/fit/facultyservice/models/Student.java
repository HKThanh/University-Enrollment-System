package vn.edu.iuh.fit.facultyservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;

    private String name;

    private int year;

    private String mail;


}
