package com.student.attendace.Student.Attendance.repository;

import com.student.attendace.Student.Attendance.model.ClassRoom;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Integer> {

    @Query("SELECT c FROM ClassRoom c WHERE c.className = :className AND c.section = :section AND c.session = :session")
    Optional<ClassRoom> findByClassNameAndSection(@Param("className") String className, @Param("section") String section, String session);

    @Query("SELECT c FROM ClassRoom c WHERE c.className = :name")
    List<ClassRoom> findAllByClassName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query("UPDATE ClassRoom c SET c.className = :#{#classRoom.className}," +
            "c.section = :#{#classRoom.section}, " +
            "c.session = :#{#classRoom.session} " +
            "WHERE c.id = :id")
    int updateClassRoomById(@Param("classRoom") ClassRoom classRoom, @Param("id") int id);

}
