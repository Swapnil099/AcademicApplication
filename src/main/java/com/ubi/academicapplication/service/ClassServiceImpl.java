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

import com.ubi.academicapplication.dto.classdto.ClassDetailDto;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.student.StudentDto;
import com.ubi.academicapplication.entity.ClassDetail;
import com.ubi.academicapplication.entity.Student;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.mapper.ClassMapper;
import com.ubi.academicapplication.mapper.StudentMapper;
import com.ubi.academicapplication.repository.ClassRepository;

@Service
public class ClassServiceImpl implements ClassService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClassServiceImpl.class);

	@Autowired
	private ClassRepository classRepository;


	@Autowired
	private ClassMapper classMapper;

	@Autowired
	private StudentMapper studentMapper;

	public Response<ClassDetailDto> addClassDetails(ClassDetailDto classDto) {

		Response<ClassDetailDto> response = new Response<>();
		Result<ClassDetailDto> res = new Result<>();

		if (classDto.getClassCode().isEmpty() || classDto.getClassCode().length() == 0) {
			throw new CustomException(HttpStatusCode.NO_CLASSCODE_FOUND.getCode(), HttpStatusCode.NO_CLASSCODE_FOUND,
					HttpStatusCode.NO_CLASSCODE_FOUND.getMessage(), res);
		}
		ClassDetail savedClass = classRepository.save(classMapper.dtoToEntity(classDto));
		response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		response.setResult(new Result<ClassDetailDto>(classMapper.entityToDto(savedClass)));
		return response;
	}

	public Response<List<ClassDetailDto>> getClassDetails(Integer PageNumber, Integer PageSize) {
		Result<List<ClassDetailDto>> res = new Result<>();

		res.setData(null);
		Pageable paging = PageRequest.of(PageNumber, PageSize);
		Response<List<ClassDetailDto>> getListofClasses = new Response<>();
		Page<ClassDetail> list = this.classRepository.findAll(paging);
		List<ClassDetailDto> classDtos = classMapper.entitiesToDtos(list.toList());
		res.setData(classDtos);
		if (list.getSize() == 0) {
			throw new CustomException(HttpStatusCode.NO_ENTRY_FOUND.getCode(), HttpStatusCode.NO_ENTRY_FOUND,
					HttpStatusCode.NO_ENTRY_FOUND.getMessage(), res);
		}
		getListofClasses.setStatusCode(200);
		getListofClasses.setResult(res);
		return getListofClasses;
	}

	public Response<ClassDetailDto> getClassById(int classidL) {

		Result<ClassDetailDto> res = new Result<>();
		res.setData(null);
		Response<ClassDetailDto> getClass = new Response<ClassDetailDto>();
		Optional<ClassDetail> cls = null;
		Result<ClassDetailDto> classResult = new Result<>();
		cls = this.classRepository.findById(classidL);
		if (!cls.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_CLASS_MATCH_WITH_ID.getCode(),
					HttpStatusCode.NO_CLASS_MATCH_WITH_ID, HttpStatusCode.NO_CLASS_MATCH_WITH_ID.getMessage(), res);
		}
		classResult.setData(classMapper.entityToDto(cls.get()));
		getClass.setStatusCode(200);
		getClass.setResult(classResult);
		return getClass;
	}

	public Response<ClassDetailDto> deleteClassById(int id) {
		Result<ClassDetailDto> res = new Result<>();

		res.setData(null);
		Optional<ClassDetail> classDetail = classRepository.findById(id);
		if (!classDetail.isPresent()) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}
		classRepository.deleteById(id);
		Response<ClassDetailDto> response = new Response<>();
		response.setMessage(HttpStatusCode.CLASS_DELETED.getMessage());
		response.setStatusCode(HttpStatusCode.CLASS_DELETED.getCode());
		response.setResult(new Result<ClassDetailDto>(classMapper.entityToDto(classDetail.get())));
		return response;
	}

	public Response<ClassDetailDto> updateClassDetails(ClassDetailDto classDetail) {
		Result<ClassDetailDto> res = new Result<>();

		res.setData(null);
		Optional<ClassDetail> existingClassContainer = classRepository.findById(classDetail.getClassId());
		if (!existingClassContainer.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_CLASS_FOUND.getCode(), HttpStatusCode.NO_CLASS_FOUND,
					HttpStatusCode.NO_CLASS_FOUND.getMessage(), res);
		}
		ClassDetailDto existingClass = classMapper.entityToDto(existingClassContainer.get());
		existingClass.setClassCode(classDetail.getClassCode());
		existingClass.setClassName(classDetail.getClassName());

		ClassDetail updateClass = classRepository.save(classMapper.dtoToEntity(existingClass));
		Response<ClassDetailDto> response = new Response<>();
		response.setMessage(HttpStatusCode.CLASS_UPDATED.getMessage());
		response.setStatusCode(HttpStatusCode.CLASS_UPDATED.getCode());
		response.setResult(new Result<>(classMapper.entityToDto(updateClass)));
		return response;
	}

	@Override
	public Response<List<StudentDto>> getClasswithStudent(int id) {

		Response<List<StudentDto>> response = new Response<>();
		Result<List<StudentDto>> res = new Result<>();
		Optional<ClassDetail> classDetail = this.classRepository.findById(id);

		if (!classDetail.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_MATCH_WITH_ID.getCode(),
					HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_MATCH_WITH_ID,
					HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_MATCH_WITH_ID.getMessage(), res);
		}

		ClassDetail classDetailss = classDetail.get();

		List<Student> student = classDetailss.getStudents();

		response.setResult(res);

		res.setData(studentMapper.entitiesToDtos(student));

		response.setStatusCode(HttpStatusCode.EDUCATIONAL_INSTITUTION_RETRIVED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.EDUCATIONAL_INSTITUTION_RETRIVED_SUCCESSFULLY.getMessage());
//		response.setResult(new Result<>(ClassDetailDto));
		return response;
	}

}
