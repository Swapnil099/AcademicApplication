package com.ubi.academicapplication.service;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.util.List;

import com.ubi.academicapplication.dto.classdto.SchholClassMappingDto;
import com.ubi.academicapplication.dto.classdto.SchoolClassDto;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.school.SchoolDto;

public interface SchoolService {
	
	Response<SchoolDto> addSchool(SchoolDto schoolDto);

	Response<List<SchoolDto>> getAllSchools(Integer PageNumber, Integer PageSize);

	Response<SchoolDto> getSchoolById(int schoolId);
	
	Response<SchoolDto> getSchoolByName(String name);

	public Response<SchoolDto> deleteSchoolById(int schoolId);

	Response<SchoolDto> updateSchool(SchoolDto schoolDto) throws ParseException;
	
	Response<SchoolClassDto> addClass(SchholClassMappingDto schoolClassMappingDto);

	Response<SchoolClassDto> getSchoolwithClass(int id);

	Response<List<SchoolDto>> getSchoolwithSort(String field);
	
	ByteArrayInputStream loadSchoolAndClass();
	
	
}
