package com.example.university.application.studentapp.dto;

import com.example.university.application.studentapp.model.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Course course;
}
