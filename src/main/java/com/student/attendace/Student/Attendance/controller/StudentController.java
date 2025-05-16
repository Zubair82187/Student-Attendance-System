package com.student.attendace.Student.Attendance.controller;

import com.student.attendace.Student.Attendance.model.StudentModel;
import com.student.attendace.Student.Attendance.service.StudentService;
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
    public ResponseEntity<StudentModel> create(@RequestBody StudentModel model){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(model));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentModel> getStudentById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<StudentModel>> getAllStudent(){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getAllStudent());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
        service.delete(id);
        return ResponseEntity.ok("deleted successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<StudentModel> update(@RequestBody StudentModel model){
        return ResponseEntity.ok().body(service.update(model));
    }

}
