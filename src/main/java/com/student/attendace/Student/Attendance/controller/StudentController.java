package com.student.attendace.Student.Attendance.controller;

import com.student.attendace.Student.Attendance.dto.StudentDTO;
import com.student.attendace.Student.Attendance.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service){
        this.service = service;
    }


    @PostMapping("/create")
    public ResponseEntity<StudentDTO> create(@RequestBody @Valid StudentDTO studentDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(studentDTO));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudent(){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getAllStudent());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.delete(id));
    }

//    @PutMapping("/update")
//    public ResponseEntity<StudentDTO> update(@RequestBody StudentDTO studentDTO){
//        return ResponseEntity.ok().body(service.update(studentDTO));
//    }

}
