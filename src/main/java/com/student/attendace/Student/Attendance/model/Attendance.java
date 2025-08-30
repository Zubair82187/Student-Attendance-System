package com.student.attendace.Student.Attendance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attendance",
uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "date"})
    }
)
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassRoom classRoom;

    private String rollno;
    private boolean attendance;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
