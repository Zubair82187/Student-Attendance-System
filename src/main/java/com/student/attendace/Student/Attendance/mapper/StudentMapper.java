package com.student.attendace.Student.Attendance.mapper;

import com.student.attendace.Student.Attendance.dto.StudentDTO;
import com.student.attendace.Student.Attendance.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDto(Student student);
    Student toStudent(StudentDTO studentDTO);
}
