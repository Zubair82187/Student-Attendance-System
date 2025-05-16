package com.student.attendace.Student.Attendance.service;

import com.student.attendace.Student.Attendance.model.AttendanceModel;
import com.student.attendace.Student.Attendance.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository repository;
    public AttendanceService(AttendanceRepository repository){
        this.repository = repository;
    }

    public AttendanceModel markAttendance(AttendanceModel model){
        return repository.save(model);
    }

    public List<AttendanceModel> getAttendance(String className, String rollno){
        return repository.getAttendanceByClassNameAndRollno(className, rollno);
    }

    public List<AttendanceModel> getAttendances(){
        return repository.findAll();
    }
}
