package com.student.attendace.Student.Attendance.repository;

import com.student.attendace.Student.Attendance.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query("SELECT s FROM Subject s WHERE s.name = :name")
    Optional<Subject> findByName(@Param("name") String name);
}
