package com.ubi.academicapplication.service;

import java.util.List;


import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.student.StudentDto;


public interface StudentService {
	Response<StudentDto> saveStudent(StudentDto studentDto);

	Response<List<StudentDto>> getStudents(Integer PageNumber, Integer PageSize);

	Response<StudentDto> getStudentById(int id);

	public Response<StudentDto> deleteById(int id);

	Response<StudentDto> updateStudent(StudentDto studentDto);
}
