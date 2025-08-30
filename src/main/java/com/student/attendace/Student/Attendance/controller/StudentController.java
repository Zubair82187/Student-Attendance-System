package com.student.attendace.Student.Attendance.controller;

import com.student.attendace.Student.Attendance.dto.student.StudentDTO;
import com.student.attendace.Student.Attendance.dto.student.StudentDTOWithAttendance;
import com.student.attendace.Student.Attendance.dto.student.StudentDTOWithClassHistory;
import com.student.attendace.Student.Attendance.dto.student.StudentDTOWithSubject;
import com.student.attendace.Student.Attendance.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService service;



    //Create a student
    @PostMapping("/create")
    public ResponseEntity<StudentDTO> create(@RequestBody @Valid StudentDTO studentDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(studentDTO));
    }

    //Get a student by student id
    @GetMapping("/id/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    //Get all students
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudent(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllStudent());
    }


    //Delete a student by student id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
    }


    //Get attendance of a student by student id
    @GetMapping("/attendance/{id}")
    public ResponseEntity<StudentDTOWithAttendance> getAttendance(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAttendanceOfStudent(id));
    }

    //Get student class history by student id
    @GetMapping("/class_history/{id}")
    public ResponseEntity<StudentDTOWithClassHistory> getClassHistory(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getClasshistoryOfStudent(id));
    }

    //Get courses enrolled by a student by student id
    @GetMapping("/course/{id}")
    public ResponseEntity<StudentDTOWithSubject> getCourse(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getCourseEnrollment(id));
    }

    //Update student record
    @PatchMapping("update/{id}")
    public ResponseEntity<String> updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateStudent(studentDTO, id));
    }
}
