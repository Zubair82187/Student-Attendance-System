package com.student.attendace.Student.Attendance.mapper;

import com.student.attendace.Student.Attendance.dto.attendance.AttendanceDTO;
import com.student.attendace.Student.Attendance.dto.attendance.AttendanceDTOBasic;
import com.student.attendace.Student.Attendance.model.Attendance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StudentMapperBasic.class, ClassRoomMapper.class})
public interface AttendanceMapper {
    AttendanceDTO toAttendanceDTO(Attendance attendance);
    Attendance toAttendance(AttendanceDTO attendanceDTO);

    AttendanceDTOBasic toAttendanceDTOBasic(Attendance attendance);
}
