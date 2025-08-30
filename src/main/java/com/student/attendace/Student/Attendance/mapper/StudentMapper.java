package com.student.attendace.Student.Attendance.mapper;

import com.student.attendace.Student.Attendance.dto.student.StudentDTO;
import com.student.attendace.Student.Attendance.dto.student.StudentDTOWithAttendance;
import com.student.attendace.Student.Attendance.dto.student.StudentDTOWithClassHistory;
import com.student.attendace.Student.Attendance.dto.student.StudentDTOWithSubject;
import com.student.attendace.Student.Attendance.model.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ClassRoomMapper.class})
public interface StudentMapper {
    //Map student to studentDTO                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             Built By Mohammad Zubair, email:zubair82187@gmail.com
    StudentDTO toDto(Student student);

    //Map StudentDTO to Student
    Student toStudent(StudentDTO studentDTO);

    //Map List<Student> to List<StudentDTO>
    List<StudentDTO> toStudent(List<Student> studentList);

    StudentDTOWithAttendance toStudentDTOWithAttendance(Student student);

    StudentDTOWithClassHistory toStudentDTOWithClassHistory(Student student);

    StudentDTOWithSubject toStudentDTOWithSubject(Student student);
}
