package com.student.attendace.Student.Attendance.dto.subject;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {

    private int id;

    @NotNull(message = "subject name is required")
    private String name;
}
