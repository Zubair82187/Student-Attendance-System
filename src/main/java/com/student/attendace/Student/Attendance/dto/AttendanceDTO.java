package com.student.attendace.Student.Attendance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceDTO {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @NotNull(message = "class name is required")
    private String className;

    @NotNull(message = "roll no is required")
    private String rollno;

    @NotNull(message = "attendance is required")
    private boolean attendance;
}
