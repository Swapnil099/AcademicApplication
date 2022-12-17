package com.ubi.academicapplication.csv;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.entity.Student;
import com.ubi.academicapplication.repository.StudentRepository;

@Service
public class StudentCSVService {
	
	 @Autowired
	  StudentRepository repository;
	  
	  public ByteArrayInputStream load() {
	    List<Student> student = repository.findAll();

	    ByteArrayInputStream in = StudentCSVHelper.StudentToCSV(student);
	    return in;
	  }
	}