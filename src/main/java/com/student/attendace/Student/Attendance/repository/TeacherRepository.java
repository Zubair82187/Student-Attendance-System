package com.student.attendace.Student.Attendance.repository;

import com.student.attendace.Student.Attendance.model.Teacher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Teacher t WHERE t.id = :id")
    int deleteById(@Param("id") int id);
}
