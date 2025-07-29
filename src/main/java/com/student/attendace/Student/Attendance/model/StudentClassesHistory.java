package com.student.attendace.Student.Attendance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "student_class_history")
public class StudentClassesHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    private ClassRoom classRoom;

    @DateTimeFormat(pattern = "DD/MM/YYYY")
    private Date joinDate;
    @DateTimeFormat(pattern = "DD/MM/YYYY")
    private Date leaveDate;
}
