package com.student.attendace.Student.Attendance.dto;


import com.student.attendace.Student.Attendance.model.Student;
import com.student.attendace.Student.Attendance.model.StudentClassesHistory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoomDTOFullEntity {
    private int id;

    @NotNull(message = "class name is required")
    private String className;

    private String section;

    @NotNull(message = "session is required")
    private String session;

    private List<Student> students;

    private List<StudentClassesHistory> studentClassesHistories;
}
