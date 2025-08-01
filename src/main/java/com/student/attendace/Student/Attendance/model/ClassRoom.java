package com.student.attendace.Student.Attendance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "classroom")
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private  String className;
    private String section;

    @Column(nullable = false)
    private String session;

    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students;

    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StudentClassesHistory> studentClassesHistories;
}
