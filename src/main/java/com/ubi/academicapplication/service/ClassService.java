package com.ubi.academicapplication.service;

import java.util.List;


import org.springframework.data.domain.Page;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.ClassDetail;




public interface ClassService {

	Response<ClassDetail> addClassDetails(ClassDetail classDetail);
	
	Response<List<ClassDetail>> getClassDetails(Integer PageNumber, Integer PageSize);

	public Response<ClassDetail> getClassById(Long classidL);
	
	public Response<ClassDetail> deleteClassById(Long classidL);

	Response<ClassDetail> updateClassDetails(ClassDetail classDetail);
	
//	public List<ClassDetail> findClassWithSorting(String field);

}

