package com.example.university.application.studentapp.mapper;

import com.example.university.application.studentapp.dto.StudentDto;
import com.example.university.application.studentapp.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDto mapStudentToStudentDTO(Student student);
}
