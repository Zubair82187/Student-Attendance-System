package com.student.attendace.Student.Attendance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@Entity(name = "student")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int age;
    private String rollno;
    private Date dob;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<StudentClassesHistory> studentClassesHistories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private ClassRoom classRoom;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<Attendance> attendance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<Subject> subject;
}
