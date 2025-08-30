package com.student.attendace.Student.Attendance.dto.classroom;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoomDTO {

    private int id;

    @NotNull(message = "class name is required")
    private String className;
    private String section;
    @NotNull(message = "session is required")
    private String session;
}
