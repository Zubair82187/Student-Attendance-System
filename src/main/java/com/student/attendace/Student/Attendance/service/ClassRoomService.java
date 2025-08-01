package com.student.attendace.Student.Attendance.service;

import com.student.attendace.Student.Attendance.dto.ClassRoomDTO;
import com.student.attendace.Student.Attendance.dto.ClassRoomDTOFullEntity;
import com.student.attendace.Student.Attendance.dto.ClassRoomDTOWithStudent;
import com.student.attendace.Student.Attendance.dto.ClassRoomDTOWithStudentClassHistory;
import com.student.attendace.Student.Attendance.exception.NotFoundException;
import com.student.attendace.Student.Attendance.mapper.ClassRoomMapper;
import com.student.attendace.Student.Attendance.model.ClassRoom;
import com.student.attendace.Student.Attendance.repository.ClassRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClassRoomService {

    private final ClassRoomRepository classRoomRepository;
    private final ClassRoomMapper classRoomMapper;



    //Create a classroom
    public ClassRoomDTO createClass(ClassRoomDTO classRoomDTO){
        ClassRoom classRoom = classRoomMapper.toClassRoom(classRoomDTO);
        classRoom = classRoomRepository.save(classRoom);
        return classRoomMapper.toClassRoomDTO(classRoom);
    }

    //Get all the classrooms
    public List<ClassRoomDTO> getAllClassRoom(){
        List<ClassRoom> classRoomList = classRoomRepository.findAll();
        if(classRoomList.isEmpty()){
            throw new NotFoundException("there is no class room.");
        }
        return classRoomMapper.toClassRoomDTO(classRoomList);
    }


    //Get all students from a class by class id
    public ClassRoomDTOWithStudent getAllStudentsFromAClass(int id){
        //get classroom by id
        Optional<ClassRoom> tempClassRoom = classRoomRepository.findById(id);

        //Extract classroom
        ClassRoom classRoom = tempClassRoom.orElseThrow(()->
                new NotFoundException("There is no class room with id "+id));

        //map the classroom to dto and return it
        return classRoomMapper.toClassRoomDtoWithStudent(classRoom);
    }

    //Get all classes by name
    public List<ClassRoomDTO> getAllClasses(String name){
        List<ClassRoom> classRoomList = classRoomRepository.findAllByClassName(name);
        if(classRoomList.isEmpty()){
            throw new NotFoundException("There is no class room with name "+name);
        }
        return classRoomMapper.toClassRoomDTO(classRoomList);
    }

    //Get all students from a class by class name and section
    public ClassRoomDTOWithStudent getAllStudentsFromAClassByClassNameAndSection(String name, String section, String session){
        //get classroom by id
        Optional<ClassRoom> tempClassRoom = classRoomRepository.findByClassNameAndSection(name, section, session);

        //Extract classroom
        ClassRoom classRoom = tempClassRoom.orElseThrow(()->
                                new NotFoundException("There is no class room with "+name +" section " +section+" and "+session));

        // return dto
        return classRoomMapper.toClassRoomDtoWithStudent(classRoom);
    }


    //Get history of a classroom by name and section
    public ClassRoomDTOWithStudentClassHistory getHistoryOfClassRoom(String name, String section, String session){
        Optional<ClassRoom> tempClassRoom = classRoomRepository.findByClassNameAndSection(name, section, session);
        ClassRoom classRoom = tempClassRoom.orElseThrow(()->
                            new NotFoundException("There is no class with name "+ name + " ans section "+section));

        return classRoomMapper.toClassRoomDtoWithStudentClassHistory(classRoom);
    }

    //Get the full classroom with all the child
    public ClassRoomDTOFullEntity getFullClassRoom(String name, String section, String session){
        Optional<ClassRoom> tempClassRoom = classRoomRepository.findByClassNameAndSection(name, section, session);
        ClassRoom classRoom = tempClassRoom.orElseThrow(()->
                new NotFoundException("There is no class room with name "+name+" and "+section));

        return classRoomMapper.toClassRoomFullDto(classRoom);
    }


    //Update classroom detail
    public String updateClassRoomById(int id, ClassRoomDTO classRoomDto){
        ClassRoom classRoom = classRoomMapper.toClassRoom(classRoomDto);
        int result = classRoomRepository.updateClassRoomById(classRoom, id);
        if(result>0){
            return "Record updated successfully";
        }
        else{
            return "Either there is no record with '"+classRoom.getClassName()+
                    "' and '"+classRoom.getSection()+"' or something went wrong.";
        }
    }
}
