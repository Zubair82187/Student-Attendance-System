package com.student.attendace.Student.Attendance.mapper;

import com.student.attendace.Student.Attendance.dto.classroom.ClassRoomDTO;
import com.student.attendace.Student.Attendance.dto.classroom.ClassRoomDTOFullEntity;
import com.student.attendace.Student.Attendance.dto.classroom.ClassRoomDTOWithStudent;
import com.student.attendace.Student.Attendance.dto.classroom.ClassRoomDTOWithStudentClassHistory;
import com.student.attendace.Student.Attendance.model.ClassRoom;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapperBasic.class})
public interface ClassRoomMapper {

    //Map classroom into classroomDtoWithStudent
    ClassRoomDTOWithStudent toClassRoomDtoWithStudent(ClassRoom classRoom);

    //Map classroom to classroomDTO
    ClassRoomDTO toClassRoomDTO(ClassRoom classRoom);

    //Map ClassRoomDTO to classroom
    ClassRoom toClassRoom(ClassRoomDTO classRoomDTO);

    //Map List of classroom to the list of classroom dto
    List<ClassRoomDTO> toClassRoomDTO(List<ClassRoom> classRoomList);

    //Map classroom to classRoomDTOWithStudentClassHistory
    ClassRoomDTOWithStudentClassHistory toClassRoomDtoWithStudentClassHistory(ClassRoom classRoom);

    //Map classroom to classRoomDTOFullEntity
    ClassRoomDTOFullEntity toClassRoomFullDto(ClassRoom classRoom);

}
