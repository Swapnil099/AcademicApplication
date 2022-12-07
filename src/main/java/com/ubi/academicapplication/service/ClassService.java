package com.ubi.academicapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ubi.academicapplication.dto.responsedto.ClassDto;
import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.ClassDetail;


public interface ClassService {

	public ClassDetail addClassDetails(ClassDto classDto);

	public Response <List<ClassDetail>> getClassDetails();

	public Response getClassById(Long classidL);

	public void deleteClassById(Long classidL);

	public ClassDetail updateClassDetails(ClassDetail classDetail);

	public Page<ClassDetail> findClassWithPagination(int offset,int pageSize);

	//		public List<ClassDetail> findClassWithSorting(String field);

}

