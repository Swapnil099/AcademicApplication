package com.ubi.academicapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class AcademicApplication  {

	public static void main(String[] args) {
		SpringApplication.run(AcademicApplication.class, args);
	}
}
