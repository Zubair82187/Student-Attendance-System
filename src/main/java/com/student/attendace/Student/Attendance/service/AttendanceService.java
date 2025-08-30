package com.student.attendace.Student.Attendance.service;

import com.student.attendace.Student.Attendance.dto.attendance.AttendanceDTO;
import com.student.attendace.Student.Attendance.dto.attendance.AttendanceDTOBasic;
import com.student.attendace.Student.Attendance.exception.NotFoundException;
import com.student.attendace.Student.Attendance.mapper.AttendanceMapper;
import com.student.attendace.Student.Attendance.model.Attendance;
import com.student.attendace.Student.Attendance.repository.AttendanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AttendanceService {
    private final AttendanceRepository repository;

    private final AttendanceMapper mapper;

    //Mark attendance
    public AttendanceDTO markAttendance(AttendanceDTO attendanceDTO){
        Attendance attendance = mapper.toAttendance(attendanceDTO);
        attendance = repository.save(attendance);
        return mapper.toAttendanceDTO(attendance);
    }

    //Get attendance by student roll no and class id
    public List<AttendanceDTOBasic> attendanceByRollNoAndClassId(int rollno, int class_id){
        List<Attendance> attendanceList = repository.attendanceByRollNoAndClassId(rollno, class_id);

        if(attendanceList.isEmpty()){
            throw new NotFoundException("There are no attendance of this roll no  "+rollno+ " in class ");
        }
        return attendanceList.stream()
                .map(mapper::toAttendanceDTOBasic)
                .collect(Collectors.toList());
    }

    //Get attendance by date
    public List<AttendanceDTOBasic> attendanceByDate(LocalDate date){
        List<Attendance> attendanceList = repository.attendanceByDate(date);
        if(attendanceList.isEmpty()){
            throw new NotFoundException("There are no attendance of date "+date);
        }

        return attendanceList.stream()
                .map(mapper::toAttendanceDTOBasic)
                .collect(Collectors.toList());
    }

    //Get attendance between dates
    public List<AttendanceDTOBasic> attendanceBetweenDates(LocalDate startDate, LocalDate endDate){
        List<Attendance> attendanceList = repository.attendanceBetweenDates(startDate, endDate);

        if(attendanceList.isEmpty()){
            throw new NotFoundException("there are no attendance betwenn "+startDate+ " to "+endDate);
        }

        return attendanceList.stream()
                .map(mapper::toAttendanceDTOBasic)
                .collect(Collectors.toList());
    }

    //Get attendance by student id
    public List<AttendanceDTOBasic> attendanceByStudentId(int student_id){
        List<Attendance> attendanceList = repository.findByStudentId(student_id);

        if(attendanceList.isEmpty()){
            throw new NotFoundException("there are no attendance of this student");
        }

        return attendanceList.stream()
                .map(mapper::toAttendanceDTOBasic)
                .collect(Collectors.toList());
    }

    //Get attendance by date and student id
    public AttendanceDTOBasic attendanceByDateAndStudentId(LocalDate date, int student_id){
        return mapper.toAttendanceDTOBasic(repository.attendanceByDateAndStudentId(date, student_id));
    }

}
