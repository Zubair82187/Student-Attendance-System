package com.student.attendace.Student.Attendance.controller;

import com.student.attendace.Student.Attendance.model.AttendanceModel;
import com.student.attendace.Student.Attendance.service.AttendanceService;
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

    @PostMapping("/markAttendance")
    public ResponseEntity<AttendanceModel> markAttendance(@RequestBody AttendanceModel attendanceModel){
        return ResponseEntity.ok().body(service.markAttendance(attendanceModel));
    }

    @GetMapping("/getAttendance/{className}/{rollno}")
    public ResponseEntity<List<AttendanceModel>> getAttendanceByClassNameAndRollno(@PathVariable String className, @PathVariable String rollno){
        return ResponseEntity.ok().body(service.getAttendance(className, rollno));
    }

    @GetMapping("/")
    public ResponseEntity<List<AttendanceModel>> getAttendances(){
        return ResponseEntity.ok().body(service.getAttendances());
    }
}
