package com.example.university.application.studentapp.controller;

import com.example.university.application.studentapp.dto.StudentDto;
import com.example.university.application.studentapp.mapper.StudentMapper;
import com.example.university.application.studentapp.model.Student;
import com.example.university.application.studentapp.service.CourseService;
import com.example.university.application.studentapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.studentMapper = studentMapper;
    }

    @PostMapping("/students")
    public Optional<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        Optional<StudentDto> createdStudent = studentService.createStudent(studentDto);
        return createdStudent;
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        List<StudentDto> studentDtos = students.stream()
                .map(studentMapper::mapStudentToStudentDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentDtos);
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long studentId) {
        try {
            Optional<Student> studentOptional = studentService.getStudentById(studentId);
            if (studentOptional.isPresent()) {
                StudentDto studentDto = studentMapper.mapStudentToStudentDTO(studentOptional.get());
                return ResponseEntity.ok(studentDto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}

