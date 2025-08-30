package com.student.attendace.Student.Attendance.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.student.attendace.Student.Attendance.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class StudentDTOWithSubject {
    private int id;

    private String name;
    private int age;
    private String rollno;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/YYYY")
    private LocalDate dob;

    private List<Subject> subject;

    public StudentDTOWithSubject(int id, String name, int age, String rollno, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.rollno = rollno;
        this.dob = dob;
    }
}
