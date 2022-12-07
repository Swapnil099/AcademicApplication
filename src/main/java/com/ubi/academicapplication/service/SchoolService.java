package com.ubi.academicapplication.service;

import java.util.List;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.School;

public interface SchoolService {

	Response<School> saveSchool(School school);

	Response<School> getsingleSchool(int id);

	Response<List<School>> getAllSchools();
	
	School updateSchool(School school);

    void deleteSchool(int id);
	
	Response getschoolByName(String name);
	
	
	
}
