package com.student.attendace.Student.Attendance.dto.student;

import com.student.attendace.Student.Attendance.dto.attendance.AttendanceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTOWithAttendance {
    private int id;

    private String name;
    private int age;
    private String rollno;
    private Date dob;
    List<AttendanceDTO> attendance;
}
