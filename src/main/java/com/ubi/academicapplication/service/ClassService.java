package com.ubi.academicapplication.service;

import java.util.List;

import com.ubi.academicapplication.dto.classdto.ClassDto;
import com.ubi.academicapplication.dto.responsedto.Response;


public interface ClassService {

	Response<ClassDto> addClassDetails(ClassDto classDto);
	
	Response<List<ClassDto>> getClassDetails(Integer PageNumber, Integer PageSize);

	public Response<ClassDto> getClassById(Long classidL);
	
	public Response<ClassDto> deleteClassById(Long classidL);

	Response<ClassDto> updateClassDetails(ClassDto classDto);
	

}

