package com.student.attendace.Student.Attendance.repository;

import com.student.attendace.Student.Attendance.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM student s WHERE s.id = :id")
    int deleteStudentById(@Param("id")int id);

    @Modifying
    @Transactional
    @Query("UPDATE student s SET " +
            "s.name = :name, " +
            "s.rollno = :rollno, " +
            "s.dob = :dob, " +
            "s.age = :age " +
            "WHERE s.id = :id")
    int updateStudent(@Param("name") String name,
                      @Param("rollno") String rollno,
                      @Param("dob") Date dob,
                      @Param("age") int age,
                      @Param("id") int id);

}
