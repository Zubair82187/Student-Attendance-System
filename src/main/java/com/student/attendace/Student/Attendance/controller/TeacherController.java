package com.student.attendace.Student.Attendance.controller;

import com.student.attendace.Student.Attendance.dto.teacher.TeacherDTO;
import com.student.attendace.Student.Attendance.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
@Validated
public class TeacherController {

    private final TeacherService teacherService;

    // ✅ Create a new teacher
    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@Valid @RequestBody TeacherDTO teacherDTO) {
        TeacherDTO createdTeacher = teacherService.createTeacher(teacherDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacher);
    }

    // ✅ Get teacher by ID
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable int id) {
        TeacherDTO teacher = teacherService.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }

    // ✅ Get all teachers with pagination
    @GetMapping
    public ResponseEntity<Page<TeacherDTO>> getAllTeachers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<TeacherDTO> teachers = teacherService.getTeachers(page, size);
        return ResponseEntity.ok(teachers);
    }

    // ✅ Delete teacher by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable int id) {
        boolean deleted = teacherService.deleteTeacherById(id);
        if (deleted) {
            return ResponseEntity.ok("Teacher deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No teacher found with id " + id);
        }
    }
}
