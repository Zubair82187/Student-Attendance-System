package com.student.attendace.Student.Attendance.service;

import com.student.attendace.Student.Attendance.exception.NotFoundException;
import com.student.attendace.Student.Attendance.model.StudentModel;
import com.student.attendace.Student.Attendance.repository.StudentRepository;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository){
        this.repository = repository;
    }


    public StudentModel create(StudentModel model){
        if(model != null && !isEmpty(model)){
            return repository.save(model);
        }
        else{
            throw new InternalException("something went wrong");
        }
    }


    public StudentModel getById(int id){
        return repository.getReferenceById(id);
    }

    public void delete(int id){
        repository.deleteById(id);
    }

    public StudentModel update(StudentModel model){
        StudentModel student = new StudentModel();

        if(!isEmpty(model)){
            student = repository.getReferenceById(model.getId());
        }
        student.setName(model.getName());
        student.setStudentClass(model.getStudentClass());
        student.setAge(model.getAge());
        student.setRollno(model.getRollno());

        return repository.save(student);
    }

    public List<StudentModel> getAllStudent(){
        List<StudentModel> list = repository.findAll();

        if(!list.isEmpty()){
            return list;
        }
        else{
            throw new NotFoundException("No student found");
        }
    }

    private boolean isEmpty(StudentModel model){
        return model.getAge() > 1 && model.getName() != null && model.getStudentClass() != null && model.getRollno() != null && model.getDob()!=null;
    }


}
