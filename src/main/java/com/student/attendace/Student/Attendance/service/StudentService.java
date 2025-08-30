package com.student.attendace.Student.Attendance.service;

import com.student.attendace.Student.Attendance.dto.student.StudentDTO;
import com.student.attendace.Student.Attendance.dto.student.StudentDTOWithAttendance;
import com.student.attendace.Student.Attendance.dto.student.StudentDTOWithClassHistory;
import com.student.attendace.Student.Attendance.dto.student.StudentDTOWithSubject;
import com.student.attendace.Student.Attendance.exception.NotFoundException;
import com.student.attendace.Student.Attendance.mapper.StudentMapper;
import com.student.attendace.Student.Attendance.model.Student;
import com.student.attendace.Student.Attendance.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper studentMapper;


    // Create Student using studentDTO
    public StudentDTO create(StudentDTO studentDTO){
        Student student = studentMapper.toStudent(studentDTO);
        student = repository.save(student);
        return studentMapper.toDto(student);
    }


    //Get a student by student id
    public StudentDTO getById(int id){
        Student student = repository.findById(id)
                .orElseThrow(()-> new NotFoundException("There is no student with id "+id));

        return studentMapper.toDto(student);
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

    //Get all the students
    public List<StudentDTO> getAllStudent(){
        List<Student> students = repository.findAll();

        if(!students.isEmpty()){
            return studentMapper.toStudent(students);
        }
        else{
            throw new NotFoundException("No student found");
        }
    }

    //Get all the attendance of a student by student id
    public StudentDTOWithAttendance getAttendanceOfStudent(int id){
        Optional<Student> tempStudent = repository.findById(id);
        Student student = tempStudent.orElseThrow(()->
                new NotFoundException("there is no student with id "+id));

        return studentMapper.toStudentDTOWithAttendance(student);
    }

    //Get Classes history of a student
    public StudentDTOWithClassHistory getClasshistoryOfStudent(int id){
        Optional<Student> tempStudent = repository.findById(id);
        Student student = tempStudent.orElseThrow(()->
                new NotFoundException("there is no student history with id "+id));

        return studentMapper.toStudentDTOWithClassHistory(student);
    }

    //Get courses enrolled by student
    public StudentDTOWithSubject getCourseEnrollment(int id){
        Optional<Student> tempStudent = repository.findById(id);
        Student student = tempStudent.orElseThrow(()->
                new NotFoundException("there is no student course enrollment with id "+id));

        return studentMapper.toStudentDTOWithSubject(student);
    }

    //Update student by id
    public String updateStudent(StudentDTO studentDTO, int id){
        Student student = studentMapper.toStudent(studentDTO);
        int result = repository.updateStudent(student.getName(),
                                                student.getRollno(),
                                                student.getDob(),
                                                student.getAge(), id);
        if(result>0){
            return "updated successfully";
        }
        else {
            return "Either student is not available or something went wrong";
        }
    }


}
