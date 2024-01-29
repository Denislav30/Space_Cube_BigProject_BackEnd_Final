package com.example.university.application.studentapp.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "students")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @ManyToOne(targetEntity = Course.class)
    @JoinColumn(name = "course_id")
    private Course course;

    public Student(String firstName, String lastName, LocalDate birthDate, Course course) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.course = course;
    }
}
