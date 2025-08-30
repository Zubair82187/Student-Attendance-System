package com.student.attendace.Student.Attendance.dto.teacher;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

    private int id;
    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "email is required")
    private String email;
}
