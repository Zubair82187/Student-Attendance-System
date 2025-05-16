package com.student.attendace.Student.Attendance.repository;

import com.student.attendace.Student.Attendance.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Integer> {
}
