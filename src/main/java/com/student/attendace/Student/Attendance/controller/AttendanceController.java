package com.student.attendace.Student.Attendance.controller;

import com.student.attendace.Student.Attendance.dto.attendance.AttendanceDTO;
import com.student.attendace.Student.Attendance.dto.attendance.AttendanceDTOBasic;
import com.student.attendace.Student.Attendance.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService service;

    @PostMapping
    public ResponseEntity<AttendanceDTO> markAttendance(@RequestBody @Valid AttendanceDTO attendanceDTO){
        return ResponseEntity.ok().body(service.markAttendance(attendanceDTO));
    }

    @GetMapping("/{rollno}/{class_id}")
    public ResponseEntity<List<AttendanceDTOBasic>> attendanceByRollNoAndClassName(@PathVariable int rollno, @PathVariable int class_id){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.attendanceByRollNoAndClassId(rollno, class_id));
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<AttendanceDTOBasic>> attendanceByDate(@PathVariable LocalDate date){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.attendanceByDate(date));
    }

    @GetMapping("/between/{startDate}/{endDate}")
    public ResponseEntity<List<AttendanceDTOBasic>> attendanceBetweenDates(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.attendanceBetweenDates(startDate, endDate));
    }

    @GetMapping("/student_id/{student_id}")
    public ResponseEntity<List<AttendanceDTOBasic>> attendanceByStudentId(@PathVariable int student_id){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.attendanceByStudentId(student_id));
    }

    @GetMapping
    public ResponseEntity<AttendanceDTOBasic> attendanceByDateAndStudentId(@RequestParam(required = false) LocalDate date, @RequestParam int student_id){
        return ResponseEntity.status(HttpStatus.FOUND).body(service.attendanceByDateAndStudentId(date, student_id));
    }
}
