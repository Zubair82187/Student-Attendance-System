package com.student.attendace.Student.Attendance.controller;

import com.student.attendace.Student.Attendance.dto.AttendanceDTO;
import com.student.attendace.Student.Attendance.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService service;
    public AttendanceController(AttendanceService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AttendanceDTO> markAttendance(@RequestBody @Valid AttendanceDTO attendanceDTO){
        return ResponseEntity.ok().body(service.markAttendance(attendanceDTO));
    }

    @GetMapping("/{className}/{rollno}")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceByClassNameAndRollno(@PathVariable String className, @PathVariable String rollno){
        return ResponseEntity.ok().body(service.getAttendance(className, rollno));
    }

    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> getAttendances(){
        return ResponseEntity.ok().body(service.getAttendances());
    }
}
