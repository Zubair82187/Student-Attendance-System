package com.student.attendace.Student.Attendance.controller;

import com.student.attendace.Student.Attendance.dto.ClassRoomDTO;
import com.student.attendace.Student.Attendance.dto.ClassRoomDTOFullEntity;
import com.student.attendace.Student.Attendance.dto.ClassRoomDTOWithStudent;
import com.student.attendace.Student.Attendance.dto.ClassRoomDTOWithStudentClassHistory;
import com.student.attendace.Student.Attendance.service.ClassRoomService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/class_room")
public class ClassRoomController {
    private final ClassRoomService classRoomService;

    @PostMapping
    public ResponseEntity<ClassRoomDTO> createClassroom(@Valid @RequestBody ClassRoomDTO classRoomDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(classRoomService.createClass(classRoomDTO));
    }

    @GetMapping
    public ResponseEntity<List<ClassRoomDTO>> getAllClassRoom(){
        return ResponseEntity.status(HttpStatus.FOUND).body(classRoomService.getAllClassRoom());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ClassRoomDTOWithStudent> getAllStudentFromAClass(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.FOUND).body(classRoomService.getAllStudentsFromAClass(id));
    }

    @GetMapping("/{name}/{section}/{session}")
    public ResponseEntity<ClassRoomDTOWithStudent> getAllStudentFromAClass(@PathVariable String name, @PathVariable String section, @PathVariable String session){
        return ResponseEntity.status(HttpStatus.FOUND).body(classRoomService.getAllStudentsFromAClassByClassNameAndSection(name, section, session));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ClassRoomDTO>> getAllClassRoomByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.FOUND).body(classRoomService.getAllClasses(name));
    }

    @GetMapping("/class_history/{name}/{section}/{session}")
    public ResponseEntity<ClassRoomDTOWithStudentClassHistory> getHistoryOfClassRoom(@PathVariable String name, @PathVariable String section, @PathVariable String session ){
        return ResponseEntity.status(HttpStatus.FOUND).body(classRoomService.getHistoryOfClassRoom(name, section, session));
    }

    @GetMapping("/full_classroom/{name}/{section}/{session}")
    public ResponseEntity<ClassRoomDTOFullEntity> getFullClassRoom(@PathVariable String name, @PathVariable String section, @PathVariable String session){
        return ResponseEntity.status(HttpStatus.FOUND).body(classRoomService.getFullClassRoom(name, section, session));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateClassRoom(@PathVariable int id, @RequestBody ClassRoomDTO classRoomDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(classRoomService.updateClassRoomById(id, classRoomDTO));
    }
}
