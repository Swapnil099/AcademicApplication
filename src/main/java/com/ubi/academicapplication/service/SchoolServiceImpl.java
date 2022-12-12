package com.ubi.academicapplication.service;

import java.text.ParseException;
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
import com.ubi.academicapplication.dto.schooldto.SchoolDto;
import com.ubi.academicapplication.dto.student.StudentDto;
import com.ubi.academicapplication.dto.transfercertificate.TransferCertificateDto;

import com.ubi.academicapplication.entity.School;
import com.ubi.academicapplication.entity.Student;
import com.ubi.academicapplication.entity.TransferCertificate;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.mapper.SchoolMapper;
import com.ubi.academicapplication.repository.SchoolRepository;


@Service
public class SchoolServiceImpl implements SchoolService{

	@Autowired
	private SchoolMapper schoolMapper;
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	Logger logger = LoggerFactory.getLogger(SchoolServiceImpl.class);
	
	//@Autowired
	//Result res;
	
	
	@Override
	public Response<SchoolDto> addSchool(SchoolDto schoolDto) {
	
		Result<SchoolDto> res=new Result<>();
		Response<SchoolDto> response = new Response<>();
		Optional<SchoolDto> savedSchool =Optional.empty();
		
	//	res.setData(null);
		  if(savedSchool.isPresent()){
	            throw new CustomException(HttpStatusCode.RESOURCE_ALREADY_EXISTS.getCode(),HttpStatusCode.RESOURCE_ALREADY_EXISTS, HttpStatusCode.RESOURCE_ALREADY_EXISTS.getMessage(),res);
		  }
		  School saveSchool = schoolRepository.save(schoolMapper.dtoToEntity(schoolDto));
			response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
			response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
			response.setResult(new Result<SchoolDto>(schoolMapper.entityToDto(saveSchool)));
			return response;
	}
	

	@Override
	public Response<List<SchoolDto>> getAllSchools(Integer PageNumber, Integer PageSize) {
		
		Result<List<SchoolDto>> res=new Result<>();
    	res.setData(null);
		Pageable paging = PageRequest.of(PageNumber, PageSize);
		Response<List<SchoolDto>> getListofSchools = new Response<>();
		Page<School> list = this.schoolRepository.findAll(paging);

		List<SchoolDto> schoolDTOs =schoolMapper.entitiesToDtos(list.toList());

		res.setData(schoolDTOs);
		if (list.getSize() == 0) {
			throw new CustomException(HttpStatusCode.NO_ENTRY_FOUND.getCode(), HttpStatusCode.NO_ENTRY_FOUND,
					HttpStatusCode.NO_ENTRY_FOUND.getMessage(), res);
		}
		getListofSchools.setStatusCode(200);
		getListofSchools.setResult(res);
		return getListofSchools;
		
	}

	@Override
	public Response<SchoolDto> getSchoolById(int schoolId) {
		
		Result<SchoolDto> res=new Result<>();
    	res.setData(null);
    		Response<SchoolDto> getSchool = new Response<SchoolDto>();
    		Optional<School> school = null;
    		school = this.schoolRepository.findById(schoolId);
    		Result<SchoolDto> schoolResult = new Result<>();
    		if (!school.isPresent()) {
    			throw new CustomException(HttpStatusCode.NO_SCHOOL_MATCH_WITH_ID.getCode(),
    					HttpStatusCode.NO_SCHOOL_MATCH_WITH_ID, HttpStatusCode.NO_SCHOOL_MATCH_WITH_ID.getMessage(), res);
    		}
    		schoolResult.setData(schoolMapper.entityToDto(school.get()));
    		getSchool.setStatusCode(200);
    		getSchool.setResult(schoolResult);
    		return getSchool;
		
	}
	
	@Override
	public Response<SchoolDto> getSchoolByName(String schoolName) {
	
		Result<SchoolDto> res=new Result<>();
    	res.setData(null);
    		Response<SchoolDto> getSchoolName = new Response<SchoolDto>();
    		Optional<School> school = null;
    		school = this.schoolRepository.findByName(schoolName);
    		System.out.println("schools are" + school);
    		Result<SchoolDto> schoolResult = new Result<>();
    		if (!school.isPresent()) {
    			throw new CustomException(HttpStatusCode.NO_SCHOOL_MATCH_WITH_NAME.getCode(),
    					HttpStatusCode.NO_SCHOOL_MATCH_WITH_NAME, HttpStatusCode.NO_SCHOOL_MATCH_WITH_NAME.getMessage(), res);
    		}
    		schoolResult.setData(schoolMapper.entityToDto(school.get()));
    		getSchoolName.setStatusCode(200);
    		getSchoolName.setResult(schoolResult);
    		return getSchoolName;
		
	}
	
	

	@Override
	public Response<SchoolDto> deleteSchoolById(int schoolId) {
		Result<SchoolDto> res=new Result<>();
		res.setData(null);
		Optional<School> school = schoolRepository.findById(schoolId);
		if (!school.isPresent()) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}
		schoolRepository.deleteById(schoolId);
		Response<SchoolDto> response = new Response<>();
		response.setMessage(HttpStatusCode.SCHOOL_DELETED.getMessage());
		response.setStatusCode(HttpStatusCode.SCHOOL_DELETED.getCode());
		response.setResult(new Result<SchoolDto>(schoolMapper.entityToDto(school.get())));
		return response;
	}

	@Override
	public Response<SchoolDto> updateSchool(SchoolDto schoolDto) throws ParseException{
		
		Result<SchoolDto> res=new Result<>();
		res.setData(null);
		//Response<School> response = new Response<>();
		Optional<School> existingSchoolUpdation = schoolRepository.findById(schoolDto.getSchoolId());
		if (!existingSchoolUpdation.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_SCHOOL_FOUND.getCode(), HttpStatusCode.NO_SCHOOL_FOUND,
					HttpStatusCode.NO_SCHOOL_FOUND.getMessage(), res);
		}
     	SchoolDto existingSchool =schoolMapper.entityToDto(existingSchoolUpdation.get());

     	existingSchool.setSchoolId(schoolDto.getSchoolId());
		existingSchool.setCode(schoolDto.getCode());
		existingSchool.setContact(schoolDto.getContact());
		existingSchool.setEmail(schoolDto.getEmail());
		existingSchool.setShift(schoolDto.getShift());
		existingSchool.setStrength(schoolDto.getStrength());
		existingSchool.setType(schoolDto.getType());
		existingSchool.setVvnAccount(schoolDto.getVvnAccount());
		existingSchool.setAddress(schoolDto.getAddress());
		existingSchool.setName(schoolDto.getName());
		existingSchool.setExemptionFlag(schoolDto.isExemptionFlag());
		School updateSchool = schoolRepository.save(schoolMapper.dtoToEntity(existingSchool));
		Response<SchoolDto> response = new Response<>();
	

		response.setMessage(HttpStatusCode.SCHOOL_UPDATED.getMessage());
		response.setStatusCode(HttpStatusCode.SCHOOL_UPDATED.getCode());
		response.setResult(new Result<>(schoolMapper.entityToDto(updateSchool)));
		return response;
	}


	
	
}


