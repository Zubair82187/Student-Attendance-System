package com.student.attendace.Student.Attendance.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class LearningClass {
    private  String name;
    private String section;

    @OneToMany
    @JoinColumn(
            
    )
    private StudentModel studentModel;
}
