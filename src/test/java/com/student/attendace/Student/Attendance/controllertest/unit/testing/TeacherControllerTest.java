package com.student.attendace.Student.Attendance.controllertest.unit.testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.attendace.Student.Attendance.controller.TeacherController;
import com.student.attendace.Student.Attendance.dto.teacher.TeacherDTO;
import com.student.attendace.Student.Attendance.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TeacherController.class)
public class TeacherControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    TeacherService service;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void create_teacher_should_return_201_created() throws Exception{
        TeacherDTO teacherDTO = new TeacherDTO(1, "zubair", "zubair@gmail.com");

        when(service.createTeacher(teacherDTO)).thenReturn(teacherDTO);

        mockMvc.perform(post("/teachers")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacherDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("zubair"))
                .andExpect(jsonPath("$.email").value("zubair@gmail.com"));
    }


    @Test
    void get_teacher_by_id_should_return_200_ok() throws Exception{
        TeacherDTO teacherDTO = new TeacherDTO(1, "zubair", "zubair@gmail.com");

        when(service.getTeacherById(1)).thenReturn(teacherDTO);

        mockMvc.perform(get("/teachers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("zubair"))
                .andExpect(jsonPath("$.email").value("zubair@gmail.com"));
    }


    @Test
    void get_all_teachers_should_return_paginated_result() throws Exception{
        TeacherDTO teacher1 = new TeacherDTO(1, "zubair", "zubair@gmail.com");
        TeacherDTO teacher2 = new TeacherDTO(2, "ubais", "ubais@gmail.com");

        Page<TeacherDTO> page = new PageImpl<>(
                Arrays.asList(teacher1, teacher2),
                PageRequest.of(0, 10),
                2);

        when(service.getTeachers(0, 10)).thenReturn(page);

        mockMvc.perform(get("/teachers?page=0&size=10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].name").value("zubair"))
                .andExpect(jsonPath("$.content[1].name").value("ubais"))
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.number").value(0));
    }


    @Test
    void delete_teacher_should_return_200_ok_when_teaher_exist() throws Exception{
        int teacherId = 1;

        when(service.deleteTeacherById(1)).thenReturn(true);

        mockMvc.perform(delete("/teachers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Teacher deleted successfully"));
    }


    @Test
    void delete_teacher_should_return_404_not_found_when_teacher_does_not_exist() throws Exception{
        int teacherId = 1;

        when(service.deleteTeacherById(1)).thenReturn(false);

        mockMvc.perform(delete("/teachers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No teacher found with id "+ teacherId));
    }
}
