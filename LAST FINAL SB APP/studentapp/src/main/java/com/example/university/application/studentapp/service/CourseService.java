package com.example.university.application.studentapp.service;

import com.example.university.application.studentapp.dto.CourseDto;
import com.example.university.application.studentapp.mapper.CourseMapper;
import com.example.university.application.studentapp.model.Course;
import com.example.university.application.studentapp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<CourseDto> getCourseById(Long courseId) {

        if (courseId == null) {
            throw new IllegalArgumentException("Course ID must not be null");
        }
        Course findedCourse = courseRepository.findById(courseId).get();
        return Optional.of(courseMapper
                .mapCourseToDto(findedCourse));
    }

    public List<String> getAllCourseNames() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(Course::getCourseName).collect(Collectors.toList());
    }

    public CourseDto createCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setCourseName(courseDto.getCourseName());
        course.setEndDate(courseDto.getEndDate());
        course.setStartDate(courseDto.getStartDate());
        courseRepository.save(course);
        return courseMapper.mapCourseToDto(course);
    }

    public Course updateCourse(Long courseId, Course updatedCourse) {
        if (courseRepository.existsById(courseId)) {
            updatedCourse.setId(courseId);
            return courseRepository.save(updatedCourse);
        } else {
            return null;
        }
    }

    public boolean deleteCourse(Long courseId) {
        if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId);
            return true;
        } else {
            return false;
        }
    }

    public Optional<CourseDto> getCourseByName(String courseName) {
        Course course = courseRepository.findByCourseName(courseName);
        return Optional.of(courseMapper.mapCourseToDto(course));
    }
}