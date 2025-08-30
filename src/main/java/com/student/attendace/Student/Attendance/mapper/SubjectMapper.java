package com.student.attendace.Student.Attendance.mapper;

import com.student.attendace.Student.Attendance.dto.subject.SubjectDTO;
import com.student.attendace.Student.Attendance.model.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectDTO toDto(Subject subject);
    Subject toSubject(SubjectDTO subjectDTO);
}
