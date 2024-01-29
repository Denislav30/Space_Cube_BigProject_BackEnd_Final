package com.example.university.application.studentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.university.application.studentapp.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByCourseName(String courseName);
}
