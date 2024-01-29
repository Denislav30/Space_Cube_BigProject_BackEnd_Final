package com.example.university.application.studentapp.controller;

import com.example.university.application.studentapp.dto.CourseDto;
import com.example.university.application.studentapp.mapper.CourseMapper;
import com.example.university.application.studentapp.model.Course;
import com.example.university.application.studentapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseController(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }

    @GetMapping("/courses")
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return courses.stream()
                .map(courseMapper::mapCourseToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/courses/{id}")
    public CourseDto getCourseById(@PathVariable Long id) {
        CourseDto findedCourse = courseService.getCourseById(id).get();
        return findedCourse;
    }

    @GetMapping("/course-names")
    public List<String> getAllCourseNames() {
        return courseService.getAllCourseNames();
    }

    @GetMapping("/get-course-by-name/{courseName}")
    public CourseDto getCourseByName(@PathVariable String courseName) {
        CourseDto findedCourse = courseService.getCourseByName(courseName).get();
        return findedCourse;
    }

    @PostMapping("/courses")
    public CourseDto createCourse(@RequestBody CourseDto courseDto) {
        CourseDto createdCourse = courseService.createCourse(courseDto);
        return createdCourse;
    }
}

