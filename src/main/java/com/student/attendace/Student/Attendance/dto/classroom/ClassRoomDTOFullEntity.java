package com.student.attendace.Student.Attendance.dto.classroom;


import com.student.attendace.Student.Attendance.model.Attendance;
import com.student.attendace.Student.Attendance.model.Student;
import com.student.attendace.Student.Attendance.model.StudentClassesHistory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassRoomDTOFullEntity {
    private int id;

    @NotNull(message = "class name is required")
    private String className;

    private String section;

    @NotNull(message = "session is required")
    private String session;

    public ClassRoomDTOFullEntity(int id, String className, String section, String session) {
        this.id = id;
        this.className = className;
        this.section = section;
        this.session = session;
    }

    private List<Student> students;

    private List<StudentClassesHistory> studentClassesHistories;

    private List<Attendance> attendance;

//    public <E> ClassRoomDTOFullEntity(int i, String s, String a, String s1, java.util.List<E> of, java.util.List<E> of1) {
//    }
}
