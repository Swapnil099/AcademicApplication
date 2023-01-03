package com.ubi.academicapplication.controller;

import java.util.List;

import com.ubi.academicapplication.dto.student.StudentPromoteDemoteDto;
import com.ubi.academicapplication.security.roleaccessinterface.IsSuperAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ubi.academicapplication.dto.pagination.PaginationResponse;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.student.StudentDto;
import com.ubi.academicapplication.dto.student.StudentVerifyDto;
import com.ubi.academicapplication.security.roleaccessinterface.IsPrincipal;
import com.ubi.academicapplication.security.roleaccessinterface.IsTeacher;
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
	@IsTeacher
	public ResponseEntity<Response<StudentDto>> addStudent(@RequestBody StudentDto studentId) {
		Response<StudentDto> response = service.saveStudent(studentId);
		return ResponseEntity.ok().body(response);
	}

	@Operation(summary = "Get All Student", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping
	@IsTeacher
	public ResponseEntity<Response<PaginationResponse<List<StudentDto>>>> getStudents(
			@RequestParam(value = "PageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "PageSize", defaultValue = "5", required = false) Integer pageSize) {
		Response<PaginationResponse<List<StudentDto>>> response = service.getStudents(pageNumber, pageSize);
		return ResponseEntity.ok().body(response);

	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete Student By Id", security = @SecurityRequirement(name = "bearerAuth"))
	@IsTeacher
	public ResponseEntity<Response<StudentDto>> deleteStudent(@PathVariable Long id) {
		Response<StudentDto> response = service.deleteById(id);
		return ResponseEntity.ok().body(response);

	}

	@GetMapping("{id}")
	@Operation(summary = "Get Student By Id", security = @SecurityRequirement(name = "bearerAuth"))
	@IsTeacher
	public ResponseEntity<Response<StudentDto>> getStudentById(@PathVariable("id") Long id) {
		Response<StudentDto> response = service.getStudentById(id);
		if (response.getStatusCode() == 200) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

//
	@PutMapping
	@Operation(summary = "Update Student", security = @SecurityRequirement(name = "bearerAuth"))
	@IsTeacher
	public ResponseEntity<Response<StudentDto>> updateStudent(@RequestBody StudentDto student) {
		Response<StudentDto> response = service.updateStudent(student);
		return ResponseEntity.ok().body(response);
	}

	
	@Operation(summary = "Change deactivate Status To Activate", security = @SecurityRequirement(name = "bearerAuth"))
	@PatchMapping("/activate/{id}")
	@IsTeacher
	public ResponseEntity<Response<StudentDto>> activateStudentById(@PathVariable Long id) {
		Response<StudentDto> response = service.changeActiveStatusToTrue(id);
		return ResponseEntity.ok().body(response);
	}

	@Operation(summary = "Change Active Status To deactivate", security = @SecurityRequirement(name = "bearerAuth"))
	@PatchMapping("/deactivate/{id}")
	@IsTeacher
	public ResponseEntity<Response<StudentDto>> deactivateStudentById(@PathVariable Long id) {
		Response<StudentDto> response = service.changeActiveStatusToFalse(id);
		return ResponseEntity.ok().body(response);
	}

	
	@Operation(summary = "Download Csv", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/download")
	  public ResponseEntity<Resource> getFile() {
	    String filename = "Student.csv";
	    InputStreamResource file = new InputStreamResource(service.load());

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
	        .contentType(MediaType.parseMediaType("application/csv"))
	        .body(file);
	  }
	
	
	@Operation(summary = "Get By field", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/field")
	public ResponseEntity<Response<List<StudentDto>>> searchRecordsViaQueryField(@RequestParam  (defaultValue = "*")String gender,
			@RequestParam (defaultValue = "*")String category, @RequestParam(defaultValue = "*") String minority) {

		Response<List<StudentDto>> students = service.findByGenderAndCategoryAndMinority(gender, category, minority);
		return ResponseEntity.ok().body(students);
	}
	
	@Operation(summary = "Verified by Teacher", security = @SecurityRequirement(name = "bearerAuth"))
	@PatchMapping("/verifiedByTeacher/{userId}")
	@IsTeacher
	public ResponseEntity<Response<List<StudentVerifyDto>>> verifyStudentById(@PathVariable String userId,@RequestBody StudentVerifyDto id) {
		Response<List<StudentVerifyDto>> response = service.verifiedByTeacher(userId,id);
		return ResponseEntity.ok().body(response);
	}
	
	@Operation(summary = "Verified by Principal", security = @SecurityRequirement(name = "bearerAuth"))
	@PatchMapping("/verifiedByPrincipal/{userId}")
	@IsPrincipal
	public ResponseEntity<Response<List<StudentVerifyDto>>> principalverifyStudentById(@PathVariable String userId,@RequestBody StudentVerifyDto id) {
		Response<List<StudentVerifyDto>> response = service.verifiedByPrincipal(userId,id);
		return ResponseEntity.ok().body(response);
	}

	@Operation(summary = "Student Promoted User By Id", security = @SecurityRequirement(name = "bearerAuth"))
	@PatchMapping ("/promote/{userId}")
	@IsTeacher
	public ResponseEntity<Response<StudentPromoteDemoteDto>>promoteStudent(@PathVariable String userId, @RequestBody StudentPromoteDemoteDto studentPromoteDemoteCreationDto) {
		Response<StudentPromoteDemoteDto> response = service.studentPromoted(userId, studentPromoteDemoteCreationDto);
		return ResponseEntity.ok().body(response);
	}




	@Operation(summary = "Student Demoted User By Id", security = @SecurityRequirement(name = "bearerAuth"))
	@PatchMapping ("/demote/{userId}")
	@IsTeacher
	public ResponseEntity<Response<StudentPromoteDemoteDto>> demoteStudent(@PathVariable String userId, @RequestBody StudentPromoteDemoteDto studentPromoteDemoteCreationDto) {
		Response<StudentPromoteDemoteDto> response = service.studentDemoted(userId, studentPromoteDemoteCreationDto);
		return ResponseEntity.ok().body(response);
	}
	
	
}
