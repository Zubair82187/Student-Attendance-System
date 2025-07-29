package com.student.attendace.Student.Attendance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subject_id;

    private String name;
    private String taught_by;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    private List<CourseEnrollment> courseEnrollments;
}
