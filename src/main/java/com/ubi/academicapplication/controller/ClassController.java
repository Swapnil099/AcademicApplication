package com.ubi.academicapplication.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubi.academicapplication.dto.classdto.ClassDto;
import com.ubi.academicapplication.dto.classdto.ClassStudentDto;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.service.ClassServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/class")
public class ClassController 
{

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassController.class);

	@Autowired
	private ClassServiceImpl classServiceImpl;
	

	@PostMapping
	@Operation(summary = "Add Class Details", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Response<ClassStudentDto>> addClassDetails(@RequestBody ClassDto classDto) {
		Response<ClassStudentDto> response = classServiceImpl.addClassDetails(classDto);
		return ResponseEntity.ok().body(response);
	}
//	
//	@Operation(summary = "Get All Class details", security = @SecurityRequirement(name = "bearerAuth"))
//	@GetMapping
//	public ResponseEntity<Response<List<ClassDto>>> getClassDetails(	
//			@RequestParam(value = "PageNumber", defaultValue = "0", required = false) Integer pageNumber,
//			@RequestParam(value = "PageSize", defaultValue = "5", required = false) Integer pageSize) {
//		Response<List<ClassDto>> response = classServiceImpl.getClassDetails(pageNumber, pageSize);
//		return ResponseEntity.ok().body(response);
//
//	}
//
//	@GetMapping("{classid}")
//	@Operation(summary = "Get Class By Id", security = @SecurityRequirement(name = "bearerAuth"))
//	public ResponseEntity<Response> getClassById(@PathVariable("classid") Long classidL){
//		Response response = classServiceImpl.getClassById(classidL);
//		if(response.getStatusCode()==200){
//			return ResponseEntity.status(HttpStatus.OK).body(response);
//		}else{
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
//		}
//	}
//
//	@DeleteMapping("{classid}")
//	@Operation(summary = "Delete Class By Id", security = @SecurityRequirement(name = "bearerAuth"))
//	public ResponseEntity<Response<ClassDto>> deleteClassId(@PathVariable Long classid) {
//		Response<ClassDto> response = classServiceImpl.deleteClassById(classid);
//		return ResponseEntity.ok().body(response);
//
//	}
//
//	@PutMapping
//	@Operation(summary = "Update Class Details", security = @SecurityRequirement(name = "bearerAuth"))
//	public ResponseEntity<Response> updateClassDetails(@RequestBody ClassDto classDetail) {
//		Response<ClassDto> response = classServiceImpl.updateClassDetails(classDetail);
//		return ResponseEntity.ok().body(response);
//	}
//
//
//	@Operation(summary = "Get student  In class", security = @SecurityRequirement(name = "bearerAuth"))
//	@GetMapping("/getClassDeatils/{id}")
//	public ResponseEntity<Response<List<StudentDto>>> getRegionInEduIst(@PathVariable Long id) {
//		Response<List<StudentDto>> response = classServiceImpl.getClasswithStudent(id);
//		return ResponseEntity.ok().body(response);
//	}
//	
//	@Operation(summary = "Get Class in Sorting", security = @SecurityRequirement(name = "bearerAuth"))
//	@GetMapping("/sort/{field}")
//	public ResponseEntity<Response<List<ClassDto>>> getClasswithSort(@PathVariable String field) {
//		Response<List<ClassDto>> response = classServiceImpl.getClasswithSort(field);
//		return ResponseEntity.ok().body(response);
//	}
//	
//	 @Operation(summary="Download file ",security=@SecurityRequirement(name= "bearerAuth"))
//	   	@GetMapping("/download")
//	   	public ResponseEntity<Resource> getCSVFileData()
//	   	{
//	   	    String filename = "class.csv";
//	   	    InputStreamResource file = new InputStreamResource(classServiceImpl.load());
//
//	   	    return ResponseEntity.ok()
//	   	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
//	   	        .contentType(MediaType.parseMediaType("application/csv"))
//	   	        .body(file);
//	   	}

}
