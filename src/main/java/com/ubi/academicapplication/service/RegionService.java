package com.ubi.academicapplication.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.ubi.academicapplication.dto.educationaldto.regionDto.RegionCreationDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.RegionDetailsDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.RegionDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.RegionSchoolDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.RegionSchoolMappingDto;
import com.ubi.academicapplication.dto.pagination.PaginationResponse;
import com.ubi.academicapplication.dto.response.Response;

public interface RegionService {

	Response<RegionDetailsDto> addRegion(RegionCreationDto regionCreationDto);

	Response<PaginationResponse<List<RegionDetailsDto>>> getRegionDetails(Integer PageNumber, Integer PageSize);

	public Response<RegionDetailsDto> getRegionById(int id);

	public Response<RegionDto> deleteRegionById(int id);

	Response<RegionDetailsDto> updateRegionDetails(RegionDto regionDto);
	
	ByteArrayInputStream load();
	
	ByteArrayInputStream Regionload();
	
	Response<RegionDto> getRegionByName(String name);
	
	Response<RegionSchoolDto> addSchool(RegionSchoolMappingDto regionSchoolMappingDto);

	Response<RegionSchoolDto> getRegionwithSchool(int id);
	
	Response<List<RegionDetailsDto>> getRegionwithSort(String field);

}

