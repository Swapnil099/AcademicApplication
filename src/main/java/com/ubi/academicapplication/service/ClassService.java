package com.ubi.academicapplication.service;

import java.util.List;

import com.ubi.academicapplication.dto.classdto.ClassDetailDto;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.student.StudentDto;


public interface ClassService {

	Response<ClassDetailDto> addClassDetails(ClassDetailDto classDto);
	
	Response<List<ClassDetailDto>> getClassDetails(Integer PageNumber, Integer PageSize);

	public Response<ClassDetailDto> getClassById(int classidL);
	
	public Response<ClassDetailDto> deleteClassById(int classidL);

	Response<ClassDetailDto> updateClassDetails(ClassDetailDto classDto);
	
	Response<List<StudentDto>> getClasswithStudent(int id);
}

