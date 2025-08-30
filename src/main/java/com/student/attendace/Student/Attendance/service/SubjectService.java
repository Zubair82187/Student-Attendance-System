package com.student.attendace.Student.Attendance.service;

import com.student.attendace.Student.Attendance.dto.subject.SubjectDTO;
import com.student.attendace.Student.Attendance.exception.NotFoundException;
import com.student.attendace.Student.Attendance.mapper.SubjectMapper;
import com.student.attendace.Student.Attendance.model.Subject;
import com.student.attendace.Student.Attendance.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper mapper;


    public SubjectDTO createSubject(SubjectDTO subjectDTO) {
        if (subjectDTO == null) {
            throw new IllegalArgumentException("Subject cannot be null");
        }
        Subject subject = mapper.toSubject(subjectDTO);
        Subject savedSubject = subjectRepository.save(subject);
        return mapper.toDto(savedSubject);
    }

    public SubjectDTO getById(int id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No subject found with id " + id));
        return mapper.toDto(subject);
    }

    public SubjectDTO getByName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        Subject subject = subjectRepository.findByName(name)
                .orElseThrow(
                        ()-> new NotFoundException("There is no subject with this name "+name)
                );
        return mapper.toDto(subject);
    }

    public void deleteById(int id) {
        if (!subjectRepository.existsById(id)) {
            throw new NotFoundException("No subject found with id " + id);
        }
        subjectRepository.deleteById(id);
    }

    public List<SubjectDTO> findAllSubject(){
        List<Subject> subjects = subjectRepository.findAll();
        if(subjects.isEmpty()){
            throw new NotFoundException("There is no subject available.");
        }

        return subjects.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
