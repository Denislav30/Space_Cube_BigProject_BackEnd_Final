package com.example.university.application.studentapp.service;

import com.example.university.application.studentapp.dto.StudentDto;
import com.example.university.application.studentapp.mapper.StudentMapper;
import com.example.university.application.studentapp.model.Course;
import com.example.university.application.studentapp.model.Student;
import com.example.university.application.studentapp.repository.CourseRepository;
import com.example.university.application.studentapp.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final CourseService courseService;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, CourseService courseService, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.courseService = courseService;
        this.studentMapper = studentMapper;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    public Optional<StudentDto> createStudent(StudentDto studentDto) {
        Student student = new Student(studentDto.getFirstName(), studentDto.getLastName(), studentDto.getBirthDate(),studentDto.getCourse());
        studentRepository.save(student);
        return Optional.of(studentMapper
                .mapStudentToStudentDTO(student));
    }

    public Student updateStudentCourse(Long studentId, Long newCourseId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Course> newCourseOptional = courseRepository.findById(newCourseId);

        if (studentOptional.isPresent() && newCourseOptional.isPresent()) {
            Student student = studentOptional.get();
            Course newCourse = newCourseOptional.get();
            student.setCourse(newCourse);
            return studentRepository.save(student);
        } else {
            throw new EntityNotFoundException("Student or course doesn't exist.");
        }
    }

    private Course getCourseById(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        return courseOptional.orElse(null);
    }
}
