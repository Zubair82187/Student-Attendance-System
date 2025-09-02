package com.student.attendace.Student.Attendance.controllertest.unit.testing;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.attendace.Student.Attendance.controller.AttendanceController;
import com.student.attendace.Student.Attendance.dto.attendance.AttendanceDTO;
import com.student.attendace.Student.Attendance.dto.attendance.AttendanceDTOBasic;
import com.student.attendace.Student.Attendance.dto.classroom.ClassRoomDTO;
import com.student.attendace.Student.Attendance.dto.student.StudentDTO;
import com.student.attendace.Student.Attendance.dto.student.StudentDTOBasic;
import com.student.attendace.Student.Attendance.service.AttendanceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.Media;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = AttendanceController.class)
public class AttendanceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    AttendanceService attendanceService;

    //Mark attendance
    @Test
    void mark_attendance_should_return_201_created()throws Exception{

        LocalDate date = LocalDate.parse("01/09/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        AttendanceDTO attendanceDTO = new AttendanceDTO(date, new ClassRoomDTO(), "21", true, new StudentDTO());

        when(attendanceService.markAttendance(attendanceDTO)).thenReturn(attendanceDTO);

        mockMvc.perform(post("/attendance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(attendanceDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.date").value("01/09/2025"))
                .andExpect(jsonPath("$.rollno").value("21"))
                .andExpect(jsonPath("$.attendance").value(true));
    }

    //attendance by roll-no and class id
    @Test
    void get_attendance_by_rollno_and_classId_should_return_200_ok()throws Exception{
        LocalDate date = LocalDate.parse("01/09/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        AttendanceDTOBasic attendanceDTOBasic = new AttendanceDTOBasic(date, new ClassRoomDTO(), "21", true, new StudentDTOBasic());

        when(attendanceService.attendanceByRollNoAndClassId(1,1)).thenReturn(
                List.of(attendanceDTOBasic));

        mockMvc.perform(get("/attendance/1/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].date").value("01/09/2025"))
                .andExpect(jsonPath("$.[0].rollno").value("21"))
                .andExpect(jsonPath("$.[0].attendance").value(true));
    }


    //Get attendance by date
    @Test
    void get_attendance_by_date_should_return_200_ok()throws Exception{
        LocalDate date = LocalDate.parse("01/09/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        AttendanceDTOBasic attendanceDTOBasic = new AttendanceDTOBasic(date, new ClassRoomDTO(), "21", true, new StudentDTOBasic());

        when(attendanceService.attendanceByDate(date)).thenReturn(List.of(attendanceDTOBasic));

        mockMvc.perform(get("/attendance/2025-09-01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].date").value("01/09/2025"))
                .andExpect(jsonPath("$.[0].rollno").value("21"))
                .andExpect(jsonPath("$.[0].attendance").value(true));
    }

    //Get attendance between dates
    @Test
    void get_attendance_between_dates_should_return_200_ok()throws Exception{
        LocalDate startDate = LocalDate.parse("12/10/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse("12/10/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate date = LocalDate.parse("12/06/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        AttendanceDTOBasic attendanceDTOBasic = new AttendanceDTOBasic(date, new ClassRoomDTO(), "21", true, new StudentDTOBasic());

        when(attendanceService.attendanceBetweenDates(startDate, endDate)).thenReturn(List.of(attendanceDTOBasic));

        mockMvc.perform(get("/attendance/between/2024-10-12/2025-10-12")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].date").value("12/06/2025"))
                .andExpect(jsonPath("$.[0].rollno").value("21"))
                .andExpect(jsonPath("$.[0].attendance").value(true));
    }

    //Get attendance by student id
    @Test
    void get_attendance_by_student_id_should_return_200_ok() throws Exception{
        LocalDate date = LocalDate.parse("12/06/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        AttendanceDTOBasic attendanceDTOBasic = new AttendanceDTOBasic(date, new ClassRoomDTO(), "21", true, new StudentDTOBasic(1, "zubair"));

        when(attendanceService.attendanceByStudentId(1)).thenReturn(List.of(attendanceDTOBasic));

        mockMvc.perform(get("/attendance/student_id/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].date").value("12/06/2025"))
                .andExpect(jsonPath("$.[0].rollno").value("21"))
                .andExpect(jsonPath("$.[0].attendance").value(true))
                .andExpect(jsonPath("$.[0].student.name").value("zubair"));

    }

    //Get student attendance of a date
    @Test
    void get_student_attendance_of_a_date_shuold_return_200_ok() throws Exception{
        LocalDate date = LocalDate.parse("12/06/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        AttendanceDTOBasic attendanceDTOBasic = new AttendanceDTOBasic(date, new ClassRoomDTO(), "21", true, new StudentDTOBasic(1, "zubair"));

        when(attendanceService.attendanceByDateAndStudentId(date, 1)).thenReturn(attendanceDTOBasic);

        mockMvc.perform(get("/attendance")
                .param("date", "2025-06-12")
                .param("student_id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rollno").value("21"))
                .andExpect(jsonPath("$.attendance").value(true))
                .andExpect(jsonPath("$.student.name").value("zubair"));
    }
}
