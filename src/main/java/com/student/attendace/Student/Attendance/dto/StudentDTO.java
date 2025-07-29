package com.student.attendace.Student.Attendance.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private int id;

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "age is required")
    private int age;

    @NotNull(message = "studentClass is required")
    private String studentClass;

    @NotNull(message = "rollno is required")
    private String rollno;

    private Date dob;
}
