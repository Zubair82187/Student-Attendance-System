package com.student.attendace.Student.Attendance.repository;

import com.student.attendace.Student.Attendance.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    List<Attendance> getAttendanceByClassNameAndRollno(String className, String rollno);
}
