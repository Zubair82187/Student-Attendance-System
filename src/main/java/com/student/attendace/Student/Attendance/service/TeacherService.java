package com.student.attendace.Student.Attendance.service;
import com.student.attendace.Student.Attendance.dto.teacher.TeacherDTO;
import com.student.attendace.Student.Attendance.exception.NotFoundException;
import com.student.attendace.Student.Attendance.mapper.TeacherMapper;
import com.student.attendace.Student.Attendance.model.Teacher;
import com.student.attendace.Student.Attendance.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    //Create Teacher
    public TeacherDTO createTeacher(TeacherDTO teacherDTO){
        return teacherMapper.toDto(teacherRepository.save(teacherMapper.toTeacher(teacherDTO)));
    }

    //Get teacher by teacher id
    public TeacherDTO getTeacherById(int id){
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        Teacher teacher = optionalTeacher.orElseThrow(()->
                new NotFoundException("there is no teacher associated with this id "+id));
        return teacherMapper.toDto(teacher);
    }

    //Get all teachers with pagination
    public Page<TeacherDTO> getTeachers(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Teacher> teachers = teacherRepository.findAll(pageable);
        return teachers.map(teacherMapper::toDto);
    }


    //Delete a teacher by id
    public boolean deleteTeacherById(int id){
        if(teacherRepository.existsById(id)){
            teacherRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }


}
