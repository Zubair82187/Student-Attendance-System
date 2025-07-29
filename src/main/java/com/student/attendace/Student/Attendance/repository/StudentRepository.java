package com.student.attendace.Student.Attendance.repository;

import com.student.attendace.Student.Attendance.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM student s WHERE s.id = :id")
    int deleteStudentById(@Param("id")int id);

//    @Modifying
//    @Transactional
//    @Query("UPDATE student s SET s.name = :#{#student.name}, " +
//                                "s.age = :#{#student.age}, " +
//                                "s.studentClass = :#{#student.studentClass}, " +
//                                "s.rollno = :#{#student.rollno}, " +
//                                "s.dob = :#{#student.dob} " +
//                                "WHERE s.id = :id")
//    int updateStudent(@Param("student") Student student, @Param("id")int id);
}
