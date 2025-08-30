package com.student.attendace.Student.Attendance.dto.classroom;

import com.student.attendace.Student.Attendance.model.StudentClassesHistory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassRoomDTOWithStudentClassHistory {
    private int id;

    @NotNull(message = "class name is required")
    private String className;

    @NotNull(message = "session is required")
    private String session;

    private String section;

    private List<StudentClassesHistory> studentClassesHistories;
}
