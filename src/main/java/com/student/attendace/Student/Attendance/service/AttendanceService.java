package com.student.attendace.Student.Attendance.service;

import com.student.attendace.Student.Attendance.dto.AttendanceDTO;
import com.student.attendace.Student.Attendance.exception.NotFoundException;
import com.student.attendace.Student.Attendance.model.Attendance;
import com.student.attendace.Student.Attendance.repository.AttendanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AttendanceService {
    private final AttendanceRepository repository;
    private final ModelMapper modelMapper;

    //Mark attendance
    public AttendanceDTO markAttendance(AttendanceDTO attendanceDTO){
        Attendance attendance = modelMapper.map(attendanceDTO, Attendance.class);
        attendance = repository.save(attendance);
        return modelMapper.map(attendance, AttendanceDTO.class);
    }

    //Get attendance by name and roll_no
    public List<AttendanceDTO> getAttendance(String className, String rollno){
        List<Attendance> attendanceModel = repository.getAttendanceByClassNameAndRollno(className, rollno);

        if(attendanceModel.isEmpty()){
            throw new NotFoundException("There is no attendance of this student.");
        }
        return attendanceModel.stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceDTO.class))
                .collect(Collectors.toList());
    }

    //Get all students attendance
    public List<AttendanceDTO> getAttendances(){
        List<Attendance> attendanceList = repository.findAll();
        if(attendanceList.isEmpty()){
            throw new NotFoundException("There is no attendance of any student");
        }

        return attendanceList.stream()
                .map(list -> modelMapper.map(list, AttendanceDTO.class))
                .collect(Collectors.toList());
    }

}
