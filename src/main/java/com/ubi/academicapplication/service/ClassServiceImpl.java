package com.ubi.academicapplication.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.ClassDetail;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.repository.ClassRepository;

@Service
public class ClassServiceImpl implements ClassService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassServiceImpl.class);

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	Result res;

	public Response<ClassDetail> addClassDetails(ClassDetail classDetail) {

		Response<ClassDetail> response = new Response<>();

		if (classDetail.getClassCode().isEmpty() || classDetail.getClassCode().length() == 0) {
			throw new CustomException(HttpStatusCode.NO_CLASSCODE_FOUND.getCode(),
					HttpStatusCode.NO_CLASSCODE_FOUND, HttpStatusCode.NO_CLASSCODE_FOUND.getMessage(), res);
		}
		ClassDetail savedClass = classRepository.save(classDetail);
		response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		response.setResult(new Result<ClassDetail>(savedClass));
		return response;
	}

	public Response<List<ClassDetail>> getClassDetails(Integer PageNumber, Integer PageSize) {
		res.setData(null);
		Pageable paging = PageRequest.of(PageNumber, PageSize);
		Response<List<ClassDetail>> getListofClasses = new Response<>();
		Page<ClassDetail> list = this.classRepository.findAll(paging);
		res.setData(list.toList());
		Result<List<ClassDetail>> result = new Result<>();
		if (list.getSize() == 0) {
			throw new CustomException(HttpStatusCode.NO_ENTRY_FOUND.getCode(), HttpStatusCode.NO_ENTRY_FOUND,
					HttpStatusCode.NO_ENTRY_FOUND.getMessage(), res);
		}
		getListofClasses.setStatusCode(200);
		getListofClasses.setResult(res);
		return getListofClasses;
	}

	public Response<ClassDetail> getClassById(Long classidL) {
		Response<ClassDetail> getClass = new Response<ClassDetail>();
		Optional<ClassDetail> cls = null;
		Result<ClassDetail> classResult = new Result<>();
		cls = this.classRepository.findById(classidL);
		if (!cls.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_CLASS_MATCH_WITH_ID.getCode(),
					HttpStatusCode.NO_CLASS_MATCH_WITH_ID, HttpStatusCode.NO_CLASS_MATCH_WITH_ID.getMessage(), res);
		}
		classResult.setData(cls.get());
		getClass.setStatusCode(200);
		getClass.setResult(classResult);
		return getClass;
	}

	public Response<ClassDetail> deleteClassById(Long id) {
		res.setData(null);
		Optional<ClassDetail> classDetail = classRepository.findById(id);
		if (!classDetail.isPresent()) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}
		classRepository.deleteById(id);
		Response<ClassDetail> response = new Response<>();
		response.setMessage(HttpStatusCode.CLASS_DELETED.getMessage());
		response.setStatusCode(HttpStatusCode.CLASS_DELETED.getCode());
		response.setResult(new Result<ClassDetail>(classDetail.get()));
		return response;
	}

	public Response<ClassDetail> updateClassDetails(ClassDetail classDetail) {
		res.setData(null);
		Optional<ClassDetail> existingStudentContainer = classRepository.findById(classDetail.getClassId());
		if (!existingStudentContainer.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_CLASS_FOUND.getCode(), HttpStatusCode.NO_CLASS_FOUND,
					HttpStatusCode.NO_CLASS_FOUND.getMessage(), res);
		}
		ClassDetail existingStudent = existingStudentContainer.get();
		existingStudent.setClassCode(classDetail.getClassCode());
		existingStudent.setClassName(classDetail.getClassName());

		ClassDetail updateStudent = classRepository.save(existingStudent);
		Response<ClassDetail> response = new Response<>();
		response.setMessage(HttpStatusCode.CLASS_UPDATED.getMessage());
		response.setStatusCode(HttpStatusCode.CLASS_UPDATED.getCode());
		response.setResult(new Result<>(updateStudent));
		return response;
	}
}
//	//sorting
//	@Override
//	public List<ClassDetail> findClassWithSorting(String field){

//		LOGGER.info("findClassWithSorting added..");

//		return  classRepository.findAll(Sort.by(Sort.Direction.ASC,field));
//	}

