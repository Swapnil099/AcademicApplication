package com.ubi.academicapplication.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.Student;
import com.ubi.academicapplication.service.StudentServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;



@RestController
@RequestMapping("/student")
public class StudentController {

	

	@Autowired
	private StudentServiceImpl service;

	@PostMapping
	@Operation(summary = "Create New Student", security = @SecurityRequirement(name = "bearerAuth"))
	public Student addStudent(@RequestBody Student studentId) {
		return service.saveStudent(studentId);
	}

	
	@Operation(summary = "Get All Student", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping
	public ResponseEntity<Response<List<Student>>> getStudents() {
	     Response<List<Student>> response=service.getStudents();
	     if(response.getStatusCode()==200)
	     {
	    	 return ResponseEntity.status(HttpStatus.OK).body(response);
	     }
	     else
	     {
	    	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
	     }
	
	}
	
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Student By Id", security = @SecurityRequirement(name = "bearerAuth"))
	public void deleteStudent(@PathVariable int id) {
	     service.deleteStudent(id);
	
	}
	
	
//
//	@GetMapping("/{id}")
//	public Optional<Student> findStudentById(@PathVariable int id) {
//		return Optional.ofNullable(service.getStudentById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id :" + id)));
//	}
	
	
	@GetMapping("{id}")
	@Operation(summary = "Get Student By Id", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Response> getStudentById(@PathVariable("id") int id)
	{
		Response response=service.getStudentById(id)
;
	     if(response.getStatusCode()==200)
	     {
	    	 return ResponseEntity.status(HttpStatus.OK).body(response);
	     }
	     else
	     {
	    	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
	     }
	}
//
	@PutMapping
	@Operation(summary = "Update Student", security = @SecurityRequirement(name = "bearerAuth"))
	public Student updateStudent(@RequestBody Student student) {
		return service.updateStudent(student);
	}

//	@DeleteMapping("/{id}")
//	public void deleteStudent(@PathVariable int id) {
//		service.deleteStudent(id);
//	}
}
