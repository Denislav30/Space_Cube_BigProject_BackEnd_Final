package com.example.university.application.studentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.university.application.studentapp.model")
@EnableJpaRepositories(basePackages = "com.example.university.application.studentapp.repository")
@ComponentScan(basePackages = "com.example.university.application.studentapp")
public class StudentappApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentappApplication.class, args);
	}

}
