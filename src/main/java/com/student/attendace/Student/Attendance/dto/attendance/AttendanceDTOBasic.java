package com.student.attendace.Student.Attendance.dto.attendance;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.student.attendace.Student.Attendance.dto.classroom.ClassRoomDTO;
import com.student.attendace.Student.Attendance.dto.student.StudentDTO;
import com.student.attendace.Student.Attendance.dto.student.StudentDTOBasic;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceDTOBasic {
    @NotNull(message = "date is required")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @NotNull(message = "class id is required")
    private ClassRoomDTO classRoom;

    @NotNull(message = "roll no is required")
    private String rollno;

    @NotNull(message = "attendance is required")
    private boolean attendance;

    @NotNull(message = "student id is required")
    private StudentDTOBasic student;
}
