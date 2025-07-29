package com.student.attendace.Student.Attendance.service;

import com.student.attendace.Student.Attendance.dto.StudentDTO;
import com.student.attendace.Student.Attendance.exception.NotFoundException;
import com.student.attendace.Student.Attendance.model.Student;
import com.student.attendace.Student.Attendance.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {

    private final ModelMapper modelMapper;
    private final StudentRepository repository;


    // Create Student using studentDTO
    public StudentDTO create(StudentDTO studentDTO){
        Student student = modelMapper.map(studentDTO, Student.class);
        student = repository.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }


    //Get student by student id
    public StudentDTO getById(int id){
        Student student = repository.findById(id)
                .orElseThrow(()-> new NotFoundException("There is no student with id "+id));

        return modelMapper.map(student, StudentDTO.class);
    }

    //delete student by student id
    public String delete(int id){
        System.out.println("ye id ayi hai " + id);
        if(repository.deleteStudentById(id)==0){
            throw new NotFoundException("There is no student with id "+id);
        }
        else{
            return "Deleted Successfully";
        }
    }

    //Update student
//    public StudentDTO update(StudentDTO studentDTO){
//        int studentModel = repository.updateStudent(modelMapper.map(studentDTO, Student.class), studentDTO.getId());
//        if(studentModel==0){
//            throw new NotFoundException("There is no such student.");
//        }
//        else{
//            return modelMapper.map(studentDTO, StudentDTO.class);
//        }
//    }

    //Get all the students
    public List<StudentDTO> getAllStudent(){
        List<Student> students = repository.findAll();

        if(!students.isEmpty()){
            return students.stream()
                    .map(student -> modelMapper.map(student, StudentDTO.class))
                    .collect(Collectors.toList());
        }
        else{
            throw new NotFoundException("No student found");
        }
    }


}
