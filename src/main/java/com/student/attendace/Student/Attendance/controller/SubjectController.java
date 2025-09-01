package com.student.attendace.Student.Attendance.controller;

import com.student.attendace.Student.Attendance.dto.subject.SubjectDTO;
import com.student.attendace.Student.Attendance.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    // ✅ Create Subject
    @PostMapping
    public ResponseEntity<SubjectDTO> createSubject(@Valid @RequestBody SubjectDTO subjectDTO) {
        SubjectDTO createdSubject = subjectService.createSubject(subjectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubject);
    }

    // ✅ Get Subject by ID
    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable int id) {
        return ResponseEntity.ok(subjectService.getById(id));
    }

    // ✅ Get Subject by Name
    @GetMapping("/by-name/{name}")
    public ResponseEntity<SubjectDTO> getSubjectByName(@PathVariable String name) {
        return ResponseEntity.ok(subjectService.getByName(name));
    }

    // ✅ Get All Subjects
    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.findAllSubject());
    }

    // ✅ Delete Subject by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable int id) {
        subjectService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}