package com.example.university.application.studentapp.mapper;

import com.example.university.application.studentapp.dto.CourseDto;
import com.example.university.application.studentapp.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDto mapCourseToDto(Course course);
}
