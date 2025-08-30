package com.student.attendace.Student.Attendance.mapper;

import com.student.attendace.Student.Attendance.dto.teacher.TeacherDTO;
import com.student.attendace.Student.Attendance.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherDTO toDto(Teacher teacher);
    Teacher toTeacher(TeacherDTO teacherDTO);
}
