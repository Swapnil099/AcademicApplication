package com.ubi.academicapplication.service;

import java.text.ParseException;
import java.util.List;

import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.school.SchoolDto;


import com.ubi.academicapplication.entity.School;

public interface SchoolService {

	/*Response<School> saveSchool(School school);

	Response<School> getsingleSchool(int id);

	Response<List<School>> getAllSchools();
	
	School updateSchool(School school);

    void deleteSchool(int id);
	
	Response getschoolByName(String name);
	
	*/
	
	Response<SchoolDto> addSchool(SchoolDto schoolDto);

	Response<List<SchoolDto>> getAllSchools(Integer PageNumber, Integer PageSize);

	Response<SchoolDto> getSchoolById(int schoolId);
	
	Response<SchoolDto> getSchoolByName(String schoolName);

	public Response<SchoolDto> deleteSchoolById(int schoolId);

	Response<SchoolDto> updateSchool(SchoolDto schoolDto) throws ParseException;
	
	
}
