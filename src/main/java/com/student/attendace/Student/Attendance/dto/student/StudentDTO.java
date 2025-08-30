package com.student.attendace.Student.Attendance.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.student.attendace.Student.Attendance.dto.classroom.ClassRoomDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private int id;

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "age is required")
    private int age;

    @NotNull(message = "rollno is required")
    private String rollno;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;

    ClassRoomDTO classRoom;

    public StudentDTO(int id, String name, int age, String rollno, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.rollno = rollno;
        this.dob = dob;
    }
}
