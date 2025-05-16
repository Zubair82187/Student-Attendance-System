package com.student.attendace.Student.Attendance.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseError {
    private LocalDateTime localDateTime;
    private String message;
    private int status;
}
