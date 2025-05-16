package com.student.attendace.Student.Attendance.repository;

import com.student.attendace.Student.Attendance.model.AttendanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceModel, Integer> {
    List<AttendanceModel> getAttendanceByClassNameAndRollno(String className, String rollno);
}
