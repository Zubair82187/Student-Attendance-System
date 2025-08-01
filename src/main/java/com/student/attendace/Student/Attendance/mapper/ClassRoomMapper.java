package com.student.attendace.Student.Attendance.mapper;

import com.student.attendace.Student.Attendance.dto.ClassRoomDTO;
import com.student.attendace.Student.Attendance.dto.ClassRoomDTOFullEntity;
import com.student.attendace.Student.Attendance.dto.ClassRoomDTOWithStudent;
import com.student.attendace.Student.Attendance.dto.ClassRoomDTOWithStudentClassHistory;
import com.student.attendace.Student.Attendance.model.ClassRoom;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface ClassRoomMapper {
    ClassRoomDTOWithStudent toClassRoomDtoWithStudent(ClassRoom classRoom);
    ClassRoom toClassRoom(ClassRoomDTOWithStudent classRoomDTOWithStudent);
    ClassRoomDTO toClassRoomDTO(ClassRoom classRoom);
    ClassRoom toClassRoom(ClassRoomDTO classRoomDTO);

    List<ClassRoomDTO> toClassRoomDTO(List<ClassRoom> classRoomList);

    ClassRoomDTOWithStudentClassHistory toClassRoomDtoWithStudentClassHistory(ClassRoom classRoom);
    ClassRoomDTOFullEntity toClassRoomFullDto(ClassRoom classRoom);

}
