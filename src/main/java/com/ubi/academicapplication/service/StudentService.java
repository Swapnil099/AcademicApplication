package com.ubi.academicapplication.service;

import java.util.List;

import com.ubi.academicapplication.dto.pagination.PaginationResponse;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.student.StudentDto;
import com.ubi.academicapplication.dto.student.StudentVerifyDto;


public interface StudentService {
	Response<StudentDto> saveStudent(StudentDto studentDto);

	Response<PaginationResponse<List<StudentDto>>> getStudents(Integer PageNumber, Integer PageSize);

	Response<StudentDto> getStudentById(Long id);

	public Response<StudentDto> deleteById(Long id);

	Response<StudentDto> updateStudent(StudentDto studentDto);
	
	Response<StudentDto> changeActiveStatusToTrue(Long id);

    Response<StudentDto> changeActiveStatusToFalse(Long id);
    
	Response<StudentDto> changeCurrentStatusToPromoted(Long id);

	Response<StudentDto> changeCurrentStatusToDemoted(Long id);
	
	Response<List<StudentDto>> findByGenderAndCategoryAndMinority(String gender,String category, String minority);
	
	Response<List<StudentVerifyDto>> verifiedByTeacher(String userId,StudentVerifyDto studentVerifyDto);
	
	Response<List<StudentVerifyDto>> verifiedByPrincipal(String userId,StudentVerifyDto studentVerifyDto);

	
	
	
}
