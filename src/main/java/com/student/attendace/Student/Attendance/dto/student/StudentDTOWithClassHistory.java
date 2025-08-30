package com.student.attendace.Student.Attendance.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.student.attendace.Student.Attendance.model.StudentClassesHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class StudentDTOWithClassHistory {
    private int id;

    private String name;
    private int age;
    private String rollno;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    public StudentDTOWithClassHistory(int id, String name, int age, String rollno, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.rollno = rollno;
        this.dob = dob;
    }

    private List<StudentClassesHistory> studentClassesHistories;
}
