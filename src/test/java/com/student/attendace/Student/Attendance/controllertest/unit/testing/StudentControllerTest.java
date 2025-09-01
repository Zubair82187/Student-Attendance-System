package com.student.attendace.Student.Attendance.controllertest.unit.testing;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.attendace.Student.Attendance.controller.StudentController;
import com.student.attendace.Student.Attendance.dto.student.StudentDTO;
import com.student.attendace.Student.Attendance.dto.student.StudentDTOWithAttendance;
import com.student.attendace.Student.Attendance.dto.student.StudentDTOWithClassHistory;
import com.student.attendace.Student.Attendance.dto.student.StudentDTOWithSubject;
import com.student.attendace.Student.Attendance.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void get_student_by_id_should_return_studentDTO_200_ok() throws Exception{

        LocalDate dob = LocalDate.parse("12/10/1999", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        StudentDTO studentDTO = new StudentDTO(1, "zubair", 25, "21", dob);

        when(studentService.getById(1)).thenReturn(studentDTO);

        mockMvc.perform(get("/student/id/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("zubair"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.rollno").value(21))
                .andExpect(jsonPath("$.dob").value("12/10/1999"));
    }


    @Test
    void create_student_should_return_studentDTO_and_200_ok() throws Exception{

        LocalDate dob = LocalDate.parse("12/10/1999", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        StudentDTO studentDTO = new StudentDTO(1, "zubair", 25, "21", dob);

        when(studentService.create(studentDTO)).thenReturn(studentDTO);

        mockMvc.perform(post("/student/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("zubair"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.rollno").value(21))
                .andExpect(jsonPath("$.dob").value("12/10/1999"));
    }


    @Test
    void get_list_of_student_should_return_listOfStudent_and_200_ok() throws Exception{

        LocalDate dob = LocalDate.parse("12/10/1999", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        StudentDTO studentDTO = new StudentDTO(1, "zubair", 25, "21", dob);

        when(studentService.getAllStudent()).thenReturn(List.of(studentDTO));

        mockMvc.perform(get("/student")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("zubair"))
                .andExpect(jsonPath("$.[0].age").value(25))
                .andExpect(jsonPath("$.[0].rollno").value(21))
                .andExpect(jsonPath("$.[0].dob").value("12/10/1999"));
    }


    @Test
    void delete_student_should_return_200_ok() throws Exception{
        when(studentService.delete(1)).thenReturn("successfully deleted");

        mockMvc.perform(delete("/student/delete/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("successfully deleted"));
    }


    @Test
    void getAttendance_Success() throws Exception {

        StudentDTOWithAttendance dto = new StudentDTOWithAttendance();
        dto.setAge(25);
        dto.setName("zubair");
        dto.setId(1);
        dto.setRollno("21");

        when(studentService.getAttendanceOfStudent(1)).thenReturn(dto);

        mockMvc.perform(get("/student/attendance/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.name").value("zubair"))
                .andExpect(jsonPath("$.rollno").value("21"));
    }


    @Test
    void getClassHistory_Success() throws Exception {

        LocalDate dob = LocalDate.parse("12/10/1999", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        StudentDTOWithClassHistory dto = new StudentDTOWithClassHistory(1, "zubair", 25, "21", dob);

        when(studentService.getClasshistoryOfStudent(1)).thenReturn(dto);

        mockMvc.perform(get("/student/class_history/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("zubair"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.rollno").value("21"))
                .andExpect(jsonPath("$.dob").value("12/10/1999"));
    }


    @Test
    void getCourse_Success() throws Exception {

        LocalDate dob = LocalDate.parse("12/10/1999", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        StudentDTOWithSubject dto = new StudentDTOWithSubject(1, "zubair", 25, "21", dob);

        when(studentService.getCourseEnrollment(1)).thenReturn(dto);

        mockMvc.perform(get("/student/course/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("zubair"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.rollno").value(21))
                .andExpect(jsonPath("$.dob").value("12/10/1999"));
    }


    @Test
    void updateStudent_Success() throws Exception {
        LocalDate dob = LocalDate.parse("12/10/1999", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        StudentDTO student = new StudentDTO(1, "Ubais", 23, "21", dob);

        when(studentService.updateStudent(student, 1)).thenReturn("Updated successfully");

        mockMvc.perform(patch("/student/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(content().string("Updated successfully"));
    }


}
