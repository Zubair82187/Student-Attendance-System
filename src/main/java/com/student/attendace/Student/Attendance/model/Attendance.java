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
@Table(
        name = "attendance",
        uniqueConstraints = @UniqueConstraint(columnNames = {"className", "rollno"})
)
public class AttendanceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    private String className;
    private String rollno;
    private boolean attendance;
}
