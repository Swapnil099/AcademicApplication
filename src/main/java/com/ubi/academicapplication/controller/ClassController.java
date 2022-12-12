package com.ubi.academicapplication.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.ubi.academicapplication.dto.response.ClassDto;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.entity.ClassDetail;
import com.ubi.academicapplication.service.ClassServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/class")
public class ClassController 
{
	//Logger implementation

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassController.class);

	@Autowired
	private ClassServiceImpl classServiceImpl;

	//@Autowired
	//private ClassMapper classMapper;

	@PostMapping
	@Operation(summary = "Add Class Details", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<ClassDetail> addClassDetails(@RequestBody ClassDto classDto){
		ClassDetail classDetailsSaved = classServiceImpl.addClassDetails(classDto);
		return new ResponseEntity<ClassDetail>(classDetailsSaved, HttpStatus.CREATED);
	}

	@Operation(summary = "Get all Class Details", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping
	public ResponseEntity<Response<List<ClassDetail>>> getClassDetails(){

		Response<List<ClassDetail>> listOfClassDetails = classServiceImpl.getClassDetails();
		if(listOfClassDetails.getStatusCode()==200)
		{
			return ResponseEntity.status(HttpStatus.OK).body(listOfClassDetails);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listOfClassDetails);
		}
	}

	@GetMapping("{classid}")
	@Operation(summary = "Get Class By Id", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Response> getClassById(@PathVariable("classid") Long classidL){

		Response response = classServiceImpl.getClassById(classidL);
		if(response.getStatusCode()==200)
		{
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
		}
	}

	@DeleteMapping("{classid}")
	@Operation(summary = "Delete Class By Id", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Void> deleteClassId(@PathVariable("classid") Long classidL){

		classServiceImpl.deleteClassById(classidL);
		LOGGER.info("deleteClassId method ended..");

		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	@Operation(summary = "Update Class Details", security = @SecurityRequirement(name = "bearerAuth"))
	@PutMapping
	public ResponseEntity<ClassDetail> updateClassDetails(@RequestBody ClassDetail classDetail){//NOSONAR
		ClassDetail classDetails = classServiceImpl.updateClassDetails(classDetail);
		return new ResponseEntity<ClassDetail>(classDetails, HttpStatus.CREATED);
	}

	//pagination

	@GetMapping("/{offset}/{pageSize}")
	@Operation(summary = "pagination", security = @SecurityRequirement(name = "bearerAuth"))
	public  ResponseEntity<Page<ClassDetail>> getUsersWithPagination(@PathVariable int offset, @PathVariable int pageSize){
		LOGGER.info("getUsersWithPagination method started..");

		Page<ClassDetail> details=this.classServiceImpl.findClassWithPagination(offset, pageSize);
		LOGGER.info("getUsersWithPagination method ended..");

		return new ResponseEntity<Page<ClassDetail>>(details,HttpStatus.OK);
	}

	//	// Sorting as per required fields

	//	@GetMapping("/sort/{field}")
	//	@Operation(summary = "Sort Class Details", security = @SecurityRequirement(name = "bearerAuth"))
	//	public ResponseEntity<List<ClassDetails>> getAllClassesBySorting(@PathVariable String field){
	//		LOGGER.info("getAllClassesBySorting method started..");

	//		List<ClassDetails> classes=this.classServiceImpl.findClassWithSorting(field);

	//		LOGGER.info("getAllClassesBySorting method ended..");
	//		return new ResponseEntity<List<ClassDetails>>(classes,HttpStatus.OK);
	//	}


}
