package com.student.attendace.Student.Attendance.controllertest.unit.testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.attendace.Student.Attendance.controller.SubjectController;

import com.student.attendace.Student.Attendance.dto.subject.SubjectDTO;
import com.student.attendace.Student.Attendance.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SubjectController.class)
public class SubjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    SubjectService subjectService;

    @Autowired
    ObjectMapper objectMapper;


    //create a subject test case
    @Test
    void create_subject_controller_should_return_201_created() throws Exception{
        SubjectDTO subjectDTO = new SubjectDTO(1, "science");

        when(subjectService.createSubject(subjectDTO)).thenReturn(subjectDTO);

        mockMvc.perform(post("/api/subjects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subjectDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("science"));
    }


    //get a subject test case
    @Test
    void get_subject_by_id_should_return_200_ok()throws Exception{
        SubjectDTO subjectDTO = new SubjectDTO(1, "science");

        when(subjectService.getById(1)).thenReturn(subjectDTO);

        mockMvc.perform(get("/api/subjects/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("science"));
    }


    //Get subject by name test case
    @Test
    void get_subject_by_name_should_return_200_ok() throws Exception{
        SubjectDTO subjectDTO = new SubjectDTO(1, "Hindi");
        when(subjectService.getByName("Hindi")).thenReturn(subjectDTO);

        mockMvc.perform(get("/api/subjects/by-name/Hindi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hindi"));

    }

    //Get all subject
    @Test
    void get_all_subject_should_return_200_ok() throws Exception{
        SubjectDTO subject1 = new SubjectDTO(1, "Hindi");
        SubjectDTO subject2 = new SubjectDTO(1, "Math");

        when(subjectService.findAllSubject()).thenReturn(Arrays.asList(subject1, subject2));

        mockMvc.perform(get("/api/subjects")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].name").value("Hindi"))
                .andExpect(jsonPath("$.[1].name").value("Math"));
    }




}

