package com.ubi.academicapplication.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ubi.academicapplication.entity.School;
import com.ubi.academicapplication.service.SchoolService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;



@RestController
@RequestMapping("/school")
public class SchoolController {

	Logger logger = LoggerFactory.getLogger(SchoolController.class);

	@Autowired
	private SchoolService schoolService;
	
	@PostMapping
	@Operation(summary = "Create New School", security = @SecurityRequirement(name = "bearerAuth"))
	public Response<School> saveSchool(@RequestBody School schools) {

		Response<School> school = this.schoolService.saveSchool(schools);

		return school;
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get School By Id", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Response> getsingleSchool(@PathVariable int id)
	{
		Response response=schoolService.getsingleSchool(id);
	     if(response.getStatusCode()==200)
	     {
	    	 return ResponseEntity.status(HttpStatus.OK).body(response);
	     }
	     else
	     {
	    	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
	     }
	}
	
	@GetMapping()
	@Operation(summary = "Get All Schools", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Response<List<School>>> getSchools() {
	     Response<List<School>> response=schoolService.getAllSchools();
	     if(response.getStatusCode()==200)
	     {
	    	 return ResponseEntity.status(HttpStatus.OK).body(response);
	     }
	     else
	     {
	    	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
	     }
	
	}
	
	@PutMapping
	@Operation(summary = "Update School", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<School> updateSchool(@RequestBody School school) { 
		

		School updateSchool = this.schoolService.updateSchool(school);

		return new ResponseEntity<>(updateSchool, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete the School", security = @SecurityRequirement(name = "bearerAuth"))
	public void deleteSchoolById(@PathVariable("id") int id) {

		this.schoolService.deleteSchool(id);
	}

	@GetMapping("/name/{name}")
	@Operation(summary = "Get School By Name", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Response> getschoolByName(@PathVariable("name") String name)
	{
		Response response=schoolService.getschoolByName(name);
	     if(response.getStatusCode()==200)
	     {
	    	 return ResponseEntity.status(HttpStatus.OK).body(response);
	     }
	     else
	    	 
	     {
	    	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
	     }
	}
	
}
