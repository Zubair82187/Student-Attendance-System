package com.student.attendace.Student.Attendance.mapper;

import com.student.attendace.Student.Attendance.dto.student.StudentDTO;
import com.student.attendace.Student.Attendance.dto.student.StudentDTOBasic;
import com.student.attendace.Student.Attendance.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapperBasic {
    Student toStudent(StudentDTO studentDTO);

    StudentDTO toDto(Student student);
    StudentDTOBasic toDTO(Student student);
}
