package com.student.attendace.Student.Attendance.repository;

import com.student.attendace.Student.Attendance.model.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    @Query("SELECT a FROM Attendance a WHERE a.rollno = :rollno AND a.classRoom.id = :classId")
    List<Attendance> attendanceByRollNoAndClassId(
            @Param("rollno") int rollno,
            @Param("classId") int classId
    );

    @Query("SELECT a FROM Attendance a WHERE a.date = :date")
    List<Attendance> attendanceByDate(@Param("date") LocalDate date);

    @Query("SELECT a FROM Attendance a WHERE a.date BETWEEN :startDate AND :endDate")
    List<Attendance> attendanceBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate")LocalDate endDate);

    @Query("SELECT a FROM Attendance a WHERE a.student.id = :student_id")
    List<Attendance> findByStudentId(@Param("student_id") int student_id);

    @Query("SELECT a FROM Attendance a WHERE a.student.id = :student_id AND a.date = :date")
    Attendance attendanceByDateAndStudentId(@Param("date") LocalDate date, @Param("student_id") int student_id);
}
