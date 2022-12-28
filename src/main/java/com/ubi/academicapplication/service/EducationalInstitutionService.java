package com.ubi.academicapplication.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.ubi.academicapplication.dto.educationaldto.EducationalInstitutionDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.EIRegionMappingDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.EducationRegionGetDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.EducationalRegionDto;
import com.ubi.academicapplication.dto.response.Response;

public interface EducationalInstitutionService {

	Response<EducationalRegionDto> addEducationalInstitution(EducationalInstitutionDto educationalInstitutionDto);

	Response<EducationalInstitutionDto> getSingleEducationalInstitution(int id);

	Response<EducationalInstitutionDto> getEducationalInstituteByName(String educationalInstitutionName);

	Response<List<EducationRegionGetDto>> getAllEducationalInstitutions(Integer pageNumber, Integer pageSize);

	Response<EducationalInstitutionDto> deleteEducationalInstitution(int id);

	Response<EducationalRegionDto> updateEducationalInstitution(
			EducationalInstitutionDto educationalInstitutionDto);

	Response<EducationalRegionDto> addRegion(EIRegionMappingDto eIRegionMappingDto);

	Response<EducationalRegionDto> getEduInstwithRegion(int id);
	
	Response<List<EducationalInstitutionDto>> getEduInstwithSort(String field);
	
	ByteArrayInputStream load();

	


}
