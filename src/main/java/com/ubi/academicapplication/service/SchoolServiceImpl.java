package com.ubi.academicapplication.service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.csv.SchoolClassCsvHelper;
import com.ubi.academicapplication.dto.classdto.ClassDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.EducationRegionGetDto;
import com.ubi.academicapplication.dto.pagination.PaginationResponse;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.school.SchoolDto;
import com.ubi.academicapplication.dto.school.SchoolRegionDto;
import com.ubi.academicapplication.entity.ClassDetail;
import com.ubi.academicapplication.entity.EducationalInstitution;
import com.ubi.academicapplication.entity.Region;
import com.ubi.academicapplication.entity.School;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.mapper.ClassMapper;
import com.ubi.academicapplication.mapper.EducationalInstitutionMapper;
import com.ubi.academicapplication.mapper.RegionMapper;
import com.ubi.academicapplication.mapper.SchoolMapper;
import com.ubi.academicapplication.repository.ClassRepository;
import com.ubi.academicapplication.repository.EducationalInstitutionRepository;
import com.ubi.academicapplication.repository.RegionRepository;
import com.ubi.academicapplication.repository.SchoolRepository;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolMapper schoolMapper;

	@Autowired
	private RegionRepository regionRepository;
	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private EducationalInstitutionRepository educationalRepository;
	
	@Autowired
	private EducationalInstitutionMapper educationalMapper;
	
	@Autowired
	private ClassMapper classMapper;

	@Autowired
	private RegionMapper regionMapper;

	Logger logger = LoggerFactory.getLogger(SchoolServiceImpl.class);

	@Override
	public Response<SchoolRegionDto> addSchool(SchoolDto schoolDto) {

		Result<SchoolRegionDto> res = new Result<>();

		Response<SchoolRegionDto> response = new Response<>();

		School schoolName = schoolRepository.getSchoolByName(schoolDto.getName());
		School schoolCode = schoolRepository.getSchoolByCode(schoolDto.getCode());

		if (schoolName != null) {
			throw new CustomException(HttpStatusCode.SCHOOL_NAME_ALREADY_EXISTS.getCode(),
					HttpStatusCode.SCHOOL_NAME_ALREADY_EXISTS, HttpStatusCode.SCHOOL_NAME_ALREADY_EXISTS.getMessage(),
					res);
		}

		if (schoolCode != null) {
			throw new CustomException(HttpStatusCode.SCHOOL_CODE_ALREADY_EXISTS.getCode(),
					HttpStatusCode.SCHOOL_CODE_ALREADY_EXISTS, HttpStatusCode.SCHOOL_CODE_ALREADY_EXISTS.getMessage(),
					res);
		}

		School school = new School();
		school.setSchoolId(schoolDto.getSchoolId());
		school.setCode(schoolDto.getCode());
		school.setName(schoolDto.getName());
		school.setEmail(schoolDto.getEmail());
		school.setContact(schoolDto.getContact());
		school.setAddress(schoolDto.getAddress());
		school.setType(schoolDto.getType());
		school.setStrength(schoolDto.getStrength());
		school.setShift(schoolDto.getShift());
		school.setExemptionFlag(schoolDto.isExemptionFlag());
		school.setVvnAccount(schoolDto.getVvnAccount());
		school.setVvnFund(schoolDto.getVvnFund());

		school.setRegion(regionRepository.getReferenceById(schoolDto.getRegionId()));

		school.setClassDetail(new HashSet<>());

		for (Long classId : schoolDto.getClassId()) {
			//System.out.println(classId);
			ClassDetail classDetail = classRepository.getReferenceById(classId);
			if (classDetail != null) {
				school.getClassDetail().add(classDetail);
				classDetail.setSchool(school);
			}
		}
		if(schoolDto.getEducationalInstitutionId() != 0){
			EducationalInstitution educationalInstitution = educationalRepository.getReferenceById(schoolDto.getEducationalInstitutionId());
			if(educationalInstitution != null){
				school.setEducationalInstitution(educationalInstitution);
			}
		}

		School savedSchool = schoolRepository.save(school);

		SchoolRegionDto schoolRegionDto = schoolMapper.toSchoolClassDto(savedSchool);
		response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		response.setResult(new Result<SchoolRegionDto>(schoolRegionDto));
		return response;
	}

	@Override
	public Response<PaginationResponse<List<SchoolRegionDto>>> getAllSchools(Integer PageNumber, Integer PageSize) {

		Result<PaginationResponse<List<SchoolRegionDto>>> allSchoolResult = new Result<>();
		Pageable paging = PageRequest.of(PageNumber, PageSize);
		Response<PaginationResponse<List<SchoolRegionDto>>> getListofSchools = new Response<>();

		Page<School> list = this.schoolRepository.findAll(paging);
		List<SchoolRegionDto> schoolDtos = new ArrayList<>();
		for (School school : list) {
			SchoolRegionDto schoolRegionDto = new SchoolRegionDto();
			schoolRegionDto.setSchoolDto(schoolMapper.entityToDtos(school));

			schoolRegionDto.setRegionDto(regionMapper.toDto(school.getRegion()));
			Set<ClassDto> classDto = school.getClassDetail().stream()
					.map(classDetail -> classMapper.entityToDto(classDetail)).collect(Collectors.toSet());

			schoolRegionDto.setClassDto(classDto);
			schoolDtos.add(schoolRegionDto);
			
		schoolRegionDto.setEducationalInstitutionDto(educationalMapper.toDto(school.getEducationalInstitution()));
					}
				
		if (list.isEmpty()) {
			throw new CustomException(HttpStatusCode.NO_SCHOOL_FOUND.getCode(), HttpStatusCode.NO_SCHOOL_FOUND,
					HttpStatusCode.NO_SCHOOL_FOUND.getMessage(), allSchoolResult);
		}
		
		PaginationResponse paginationResponse=new PaginationResponse<List<SchoolRegionDto>>(schoolDtos,list.getTotalPages(),list.getTotalElements());

		
		allSchoolResult.setData(paginationResponse);
		getListofSchools.setStatusCode(HttpStatusCode.SCHOOL_RETRIVED_SUCCESSFULLY.getCode());
		getListofSchools.setMessage(HttpStatusCode.SCHOOL_RETRIVED_SUCCESSFULLY.getMessage());
		getListofSchools.setResult(allSchoolResult);
		return getListofSchools;
	}

	@Override
	public Response<SchoolRegionDto> getSchoolById(int schoolId) {

		Response<SchoolRegionDto> getSchool = new Response<>();
		Optional<School> sch = this.schoolRepository.findById(schoolId);
		Result<SchoolRegionDto> schoolResult = new Result<>();
		if (!sch.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_SCHOOL_MATCH_WITH_ID.getCode(),
					HttpStatusCode.NO_SCHOOL_MATCH_WITH_ID, HttpStatusCode.NO_SCHOOL_MATCH_WITH_ID.getMessage(),
					schoolResult);
		}

		SchoolRegionDto schoolRegionDto = new SchoolRegionDto();
		schoolRegionDto.setSchoolDto(schoolMapper.entityToDto(sch.get()));
		schoolRegionDto.setRegionDto(regionMapper.toDto(sch.get().getRegion()));
		schoolRegionDto.setClassDto(classMapper.entitiesToDto(sch.get().getClassDetail()));
		schoolRegionDto.setEducationalInstitutionDto(educationalMapper.toDto(sch.get().getEducationalInstitution()));
		schoolResult.setData(schoolRegionDto);
		getSchool.setStatusCode(HttpStatusCode.SCHOOL_RETRIVED_SUCCESSFULLY.getCode());
		getSchool.setMessage(HttpStatusCode.SCHOOL_RETRIVED_SUCCESSFULLY.getMessage());
		getSchool.setResult(schoolResult);
		return getSchool;

	}

	@Override
	public Response<SchoolRegionDto> getSchoolByName(String name) {

		Result<SchoolRegionDto> res = new Result<>();
		Response<SchoolRegionDto> getSchoolName = new Response<>();
		Optional<School> sch = this.schoolRepository.findByname(name);
		Result<SchoolRegionDto> schoolResult = new Result<>();
		if (!sch.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_SCHOOL_NAME_FOUND.getCode(),
					HttpStatusCode.NO_SCHOOL_NAME_FOUND, HttpStatusCode.NO_SCHOOL_NAME_FOUND.getMessage(), res);
		}
		SchoolRegionDto schoolRegionDto = new SchoolRegionDto();
		schoolRegionDto.setSchoolDto(schoolMapper.entityToDto(sch.get()));
		schoolRegionDto.setRegionDto(regionMapper.toDto(sch.get().getRegion()));
		schoolRegionDto.setClassDto(classMapper.entitiesToDto(sch.get().getClassDetail()));
		schoolRegionDto.setEducationalInstitutionDto(educationalMapper.toDto(sch.get().getEducationalInstitution()));
		schoolResult.setData(schoolRegionDto);
		getSchoolName.setStatusCode(HttpStatusCode.SCHOOL_RETRIVED_SUCCESSFULLY.getCode());
		getSchoolName.setMessage(HttpStatusCode.SCHOOL_RETRIVED_SUCCESSFULLY.getMessage());
		getSchoolName.setResult(schoolResult);
		return getSchoolName;
	}

	@Override
	public Response<SchoolDto> deleteSchoolById(int schoolId) {
		Result<SchoolDto> res = new Result<>();
		res.setData(null);
		Optional<School> school = schoolRepository.findById(schoolId);
		if (!school.isPresent()) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}
		Region region = school.get().getRegion();
		region.getSchool().remove(school.get());
		regionRepository.save(region);
		for (ClassDetail classDetail : school.get().getClassDetail()) {
			classDetail.setSchool(null);
			classRepository.save(classDetail);
			
			EducationalInstitution educationalInstitution = school.get().getEducationalInstitution();
			educationalInstitution.getSchool().remove(school.get());
			educationalRepository.save(educationalInstitution );
			
		}
		
		schoolRepository.deleteById(schoolId);
		Response<SchoolDto> response = new Response<>();
		response.setMessage(HttpStatusCode.SCHOOL_DELETED.getMessage());
		response.setStatusCode(HttpStatusCode.SCHOOL_DELETED.getCode());
		response.setResult(new Result<SchoolDto>(schoolMapper.entityToDto(school.get())));
		return response;
	}

	@Override
	public Response<SchoolRegionDto> updateSchool(SchoolDto schoolDto) {

		Result<SchoolRegionDto> res = new Result<>();

		res.setData(null);
		Optional<School> existingSchool = schoolRepository.findById(schoolDto.getSchoolId());
		if (!existingSchool.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_SCHOOL_FOUND.getCode(), HttpStatusCode.NO_SCHOOL_FOUND,
					HttpStatusCode.NO_SCHOOL_FOUND.getMessage(), res);
		}
		School school = existingSchool.get();
		
		SchoolDto existingSchools = schoolMapper.entityToDto(existingSchool.get());
		school.setCode(schoolDto.getCode());
		school.setContact(schoolDto.getContact());
		school.setAddress(schoolDto.getAddress());
		school.setExemptionFlag(schoolDto.isExemptionFlag());
		school.setName(schoolDto.getName());
		school.setStrength(schoolDto.getStrength());
		school.setVvnAccount(schoolDto.getVvnAccount());
		school.setVvnFund(schoolDto.getVvnFund());
		school.setStrength(schoolDto.getStrength());
		school.setShift(schoolDto.getShift());
		school.setType(schoolDto.getType());
		school.setEmail(schoolDto.getEmail());
		school.setSchoolId(schoolDto.getSchoolId());
		
		Region region = regionRepository.getReferenceById(schoolDto.getRegionId());
		regionRepository.save(region);
		school.setRegion(region);
		
		
		  for (Long classId : schoolDto.getClassId()) {
			  ClassDetail classDetail = classRepository.getReferenceById(classId);
			  classDetail.setSchool(school);
			  classRepository.save(classDetail);
			  school.getClassDetail().add(classDetail);
		  }
		  
		  EducationalInstitution educationalInstitution = educationalRepository.getReferenceById(schoolDto.getEducationalInstitutionId());
		  educationalRepository.save(educationalInstitution);
		  school.setEducationalInstitution(educationalInstitution);
		  
		  
		  
		  School updatedSchool = schoolRepository.save(school);
		  
		SchoolRegionDto schoolRegionDto = schoolMapper.toSchoolClassDto(updatedSchool);
		
		
		Response<SchoolRegionDto> response = new Response<>();
		response.setMessage(HttpStatusCode.SCHOOL_UPDATED.getMessage());
		response.setStatusCode(HttpStatusCode.SCHOOL_UPDATED.getCode());
		response.setResult(new Result<SchoolRegionDto>(schoolRegionDto));
		return response;
	}

	
	
	@Override
	public Response<List<SchoolDto>> getSchoolwithSort(String field) {

		Result<List<SchoolDto>> allSchoolResult = new Result<>();
		Response<List<SchoolDto>> getListofSchools = new Response<>();

		List<School> list = this.schoolRepository.findAll(Sort.by(Sort.Direction.ASC, field));
		List<SchoolDto> schoolDtos = schoolMapper.entitiesToDtos(list);

		if (list.size() == 0) {
			throw new CustomException(HttpStatusCode.NO_SCHOOL_FOUND.getCode(), HttpStatusCode.NO_SCHOOL_FOUND,
					HttpStatusCode.NO_SCHOOL_FOUND.getMessage(), allSchoolResult);
		}
		allSchoolResult.setData(schoolDtos);
		getListofSchools.setStatusCode(HttpStatusCode.SCHOOL_RETRIVED_SUCCESSFULLY.getCode());
		getListofSchools.setMessage(HttpStatusCode.SCHOOL_RETRIVED_SUCCESSFULLY.getMessage());
		getListofSchools.setResult(allSchoolResult);
		return getListofSchools;
	}
	
	
	@Override
	public ByteArrayInputStream loadSchoolAndClass() {
		List<School> school = schoolRepository.findAll();
		ByteArrayInputStream out = SchoolClassCsvHelper.regionCSV(school);
		return out;
	}
}

