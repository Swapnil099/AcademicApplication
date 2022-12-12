package com.ubi.academicapplication.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.dto.response.ClassDto;
import com.ubi.academicapplication.dto.response.Response;
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

	@Override
	public ClassDetail addClassDetails(ClassDto classDto) {


		LOGGER.info("addClassDetails added..");
		ClassDetail classDetail=new ClassDetail();
		classDetail.setClassCode(classDto.getClassCode());
		classDetail.setClassName(classDto.getClassName());

		return classRepository.save(classDetail);

	}

	public Response<List<ClassDetail>> getClassDetails() {
		Response<List<ClassDetail>> getListofClasses = new Response<List<ClassDetail>>();
		List<ClassDetail> list = (List<ClassDetail>) this.classRepository.findAll();
		res.setData(list);
		Result<List<ClassDetail>> allClassResult = new Result<>();
		if (list.size() == 0) {
			throw new CustomException(HttpStatusCode.NO_ENTRY_FOUND.getCode(), HttpStatusCode.NO_ENTRY_FOUND,
					HttpStatusCode.NO_ENTRY_FOUND.getMessage(), res);
		}

		allClassResult.setData(list);
		getListofClasses.setStatusCode(200);
		getListofClasses.setResult(allClassResult);
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

	@Override
	public void deleteClassById(Long classidL) {

		LOGGER.info("deleteClassById added..");

		classRepository.deleteById(classidL);
	}

	@Override
	public ClassDetail updateClassDetails(ClassDetail classDetail) {
		ClassDetail savedClassDetails = classRepository.save(classDetail);
		return savedClassDetails;

	}

	//pagination
	@Override
	public Page<ClassDetail> findClassWithPagination(int offset,int pageSize){

		LOGGER.info("findClassWithPagination added..");

		return classRepository.findAll(PageRequest.of(offset, pageSize));

	}
//	//sorting
//	@Override
//	public List<ClassDetail> findClassWithSorting(String field){

//		LOGGER.info("findClassWithSorting added..");

//		return  classRepository.findAll(Sort.by(Sort.Direction.ASC,field));
//	}
}
