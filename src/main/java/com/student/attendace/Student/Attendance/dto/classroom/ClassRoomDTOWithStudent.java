package com.student.attendace.Student.Attendance.dto.classroom;

import com.student.attendace.Student.Attendance.dto.student.StudentDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoomDTOWithStudent {
    private int id;

    @NotNull(message = "class name is required")
    private String className;

    @NotNull(message = "session is required")
    private String session;

    private String section;

    private List<StudentDTO> students;
}
