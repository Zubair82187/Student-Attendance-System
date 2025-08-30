package com.student.attendace.Student.Attendance.controllertest.unit.testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.attendace.Student.Attendance.controller.ClassRoomController;
import com.student.attendace.Student.Attendance.dto.classroom.ClassRoomDTO;
import com.student.attendace.Student.Attendance.dto.classroom.ClassRoomDTOFullEntity;
import com.student.attendace.Student.Attendance.dto.classroom.ClassRoomDTOWithStudent;
import com.student.attendace.Student.Attendance.dto.classroom.ClassRoomDTOWithStudentClassHistory;
import com.student.attendace.Student.Attendance.dto.student.StudentDTO;
import com.student.attendace.Student.Attendance.service.ClassRoomService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClassRoomController.class)
public class ClassRoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClassRoomService classRoomService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void get_All_Students_From_A_Class_By_ClassId_ShouldReturn200_Ok_AndBodyWhenStudentsExist() throws Exception {
        // Arrange
        ClassRoomDTO classroom = new ClassRoomDTO(1, "b.tech", "cs", "2024-2025");
        LocalDate dob = LocalDate.parse("12/10/1999", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        StudentDTO student = new StudentDTO(1, "Zubair", 22, "21", dob, classroom);

        ClassRoomDTOWithStudent dto = new ClassRoomDTOWithStudent(
                1,
                "8th",
                "2024-2025",
                "A",
                List.of(student)
        );

        when(classRoomService.getAllStudentsFromAClass(1)).thenReturn(dto);

        // Act & Assert
        mockMvc.perform(get("/class_room/id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.className").value("8th"))
                .andExpect(jsonPath("$.session").value("2024-2025"))
                .andExpect(jsonPath("$.section").value("A"))
                .andExpect(jsonPath("$.students[0].name").value("Zubair"))
                .andExpect(jsonPath("$.students[0].classRoom.className").value("b.tech"));
    }

    @Test
    void get_All_ClassRoom_Should_Return_200_Ok_And_List_Of_ClassRoomDTO() throws Exception{
        ClassRoomDTO classRoomDTO = new ClassRoomDTO(1, "b.tech", "cs", "2024-2025");
        when(classRoomService.getAllClassRoom()).thenReturn(List.of(classRoomDTO));

        mockMvc.perform(get("/class_room")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].className").value("b.tech"))
                .andExpect(jsonPath("$.[0].section").value("cs"))
                .andExpect(jsonPath("$.[0].session").value("2024-2025"));
    }

    @Test
    void get_All_Students_From_A_Class_Should_Return_200_Ok_And_List_Of_ClassRoomDTOWithStudent() throws Exception{
        ClassRoomDTO classroom = new ClassRoomDTO(1, "b.tech", "cs", "2024-2025");
        LocalDate dob = LocalDate.parse("12/10/1999", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        StudentDTO student = new StudentDTO(1, "Zubair", 22, "21", dob, classroom);

        ClassRoomDTOWithStudent dto = new ClassRoomDTOWithStudent(
                1,
                "8th",
                "2024-2025",
                "A",
                List.of(student)
        );

        when(classRoomService.getAllStudentsFromAClassByClassNameAndSection("b.tech", "A", "2024-2025"))
                .thenReturn(dto);


        // Act & Assert
        mockMvc.perform(get("/class_room/b.tech/A/2024-2025")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.className").value("8th"))
                .andExpect(jsonPath("$.session").value("2024-2025"))
                .andExpect(jsonPath("$.section").value("A"))
                .andExpect(jsonPath("$.students[0].name").value("Zubair"))
                .andExpect(jsonPath("$.students[0].classRoom.className").value("b.tech"));
    }

    @Test
    void create_Classroom_Should_Return_201_Created_And_Body() throws Exception {
        ClassRoomDTO request = new ClassRoomDTO(1, "b.tech", "A", "2024-2025");
        when(classRoomService.createClass(request)).thenReturn(request);

        mockMvc.perform(post("/class_room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.className").value("b.tech"))
                .andExpect(jsonPath("$.section").value("A"))
                .andExpect(jsonPath("$.session").value("2024-2025"));
    }

    @Test
    void get_All_ClassRoom_By_Name_Should_Return_200_Ok() throws Exception {
        ClassRoomDTO dto = new ClassRoomDTO(1, "b.tech", "A", "2024-2025");
        when(classRoomService.getAllClasses("b.tech")).thenReturn(List.of(dto));

        mockMvc.perform(get("/class_room/name/b.tech")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].className").value("b.tech"))
                .andExpect(jsonPath("$[0].section").value("A"));
    }

    @Test
    void get_History_Of_ClassRoom_Should_Return_200_Ok() throws Exception {
        ClassRoomDTOWithStudentClassHistory dto =
                new ClassRoomDTOWithStudentClassHistory(1, "b.tech", "2024-2025", "A", List.of());
        when(classRoomService.getHistoryOfClassRoom("b.tech", "A", "2024-2025")).thenReturn(dto);

        mockMvc.perform(get("/class_room/class_history/b.tech/A/2024-2025")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.className").value("b.tech"))
                .andExpect(jsonPath("$.section").value("A"))
                .andExpect(jsonPath("$.session").value("2024-2025"));
    }

    @Test
    void get_Full_ClassRoom_Should_Return_200_Ok() throws Exception {
        ClassRoomDTOFullEntity dto =
                new ClassRoomDTOFullEntity(1, "b.tech", "A", "2024-2025");
        when(classRoomService.getFullClassRoom("b.tech", "A", "2024-2025")).thenReturn(dto);

        mockMvc.perform(get("/class_room/full_classroom/b.tech/A/2024-2025")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.className").value("b.tech"))
                .andExpect(jsonPath("$.section").value("A"))
                .andExpect(jsonPath("$.session").value("2024-2025"));
    }

    @Test
    void update_ClassRoom_Should_Return_202_Accepted() throws Exception {
        ClassRoomDTO request = new ClassRoomDTO(1, "b.tech", "A", "2024-2025");
        when(classRoomService.updateClassRoomById(1, request)).thenReturn("Updated Successfully");

        mockMvc.perform(patch("/class_room/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Updated Successfully"));
    }
}
