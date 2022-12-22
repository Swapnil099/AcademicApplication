package com.ubi.academicapplication.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.ubi.academicapplication.dto.classdto.ClassDto;
import com.ubi.academicapplication.dto.contactinfodto.ContactInfoDto;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.student.StudentDto;



public interface ClassService {

	Response<ClassDto> addClassDetails(ClassDto classDto);
	
	Response<List<ClassDto>> getClassDetails(Integer PageNumber, Integer PageSize);

	public Response<ClassDto> getClassById(Long classidL);
	
	public Response<ClassDto> deleteClassById(Long classidL);

	Response<ClassDto> updateClassDetails(ClassDto classDto);
	
	Response<ClassDto> getClassByName(String className);
	
	Response<List<StudentDto>> getClasswithStudent(Long id);
	
	Response<List<ClassDto>> getClasswithSort(String field);
	
	ByteArrayInputStream load();
	
}

