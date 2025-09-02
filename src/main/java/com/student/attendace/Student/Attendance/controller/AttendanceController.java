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

    //Mark attendance
    @PostMapping
    public ResponseEntity<AttendanceDTO> markAttendance(@RequestBody @Valid AttendanceDTO attendanceDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.markAttendance(attendanceDTO));
    }

    //Get attendance by roll-no and class id
    @GetMapping("/{rollno}/{class_id}")
    public ResponseEntity<List<AttendanceDTOBasic>> attendanceByRollNoAndClassName(@PathVariable int rollno, @PathVariable int class_id){
        return ResponseEntity.status(HttpStatus.OK).body(service.attendanceByRollNoAndClassId(rollno, class_id));
    }

    //get attendance by date
    @GetMapping("/{date}")
    public ResponseEntity<List<AttendanceDTOBasic>> attendanceByDate(@PathVariable LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(service.attendanceByDate(date));
    }

    //get attendance between dates
    @GetMapping("/between/{startDate}/{endDate}")
    public ResponseEntity<List<AttendanceDTOBasic>> attendanceBetweenDates(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        return ResponseEntity.status(HttpStatus.OK).body(service.attendanceBetweenDates(startDate, endDate));
    }

    //get attendance by student id
    @GetMapping("/student_id/{student_id}")
    public ResponseEntity<List<AttendanceDTOBasic>> attendanceByStudentId(@PathVariable int student_id){
        return ResponseEntity.status(HttpStatus.OK).body(service.attendanceByStudentId(student_id));
    }

    //get attendance of a date by student id
    @GetMapping
    public ResponseEntity<AttendanceDTOBasic> attendanceByDateAndStudentId(@RequestParam(required = false) LocalDate date, @RequestParam int student_id){
        return ResponseEntity.status(HttpStatus.OK).body(service.attendanceByDateAndStudentId(date, student_id));
    }
}
