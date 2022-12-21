package com.ubi.academicapplication.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.ubi.academicapplication.dto.regionDto.RegionDto;
import com.ubi.academicapplication.dto.regionDto.RegionSchoolDto;
import com.ubi.academicapplication.dto.regionDto.RegionSchoolMappingDto;
import com.ubi.academicapplication.dto.response.Response;

public interface RegionService {

	Response<RegionDto> addRegion(RegionDto regionDto);

	Response<List<RegionDto>> getRegionDetails(Integer PageNumber, Integer PageSize);

	public Response<RegionDto> getRegionById(int id);

	public Response<RegionDto> deleteRegionById(int id);

	Response<RegionDto> updateRegionDetails(RegionDto regionDto);
	
	ByteArrayInputStream load();
	
	ByteArrayInputStream Regionload();
	
	Response<RegionDto> getRegionByName(String name);
	
	Response<RegionSchoolDto> addSchool(RegionSchoolMappingDto regionSchoolMappingDto);

	Response<RegionSchoolDto> getRegionwithSchool(int id);
	
	Response<List<RegionDto>> getRegionwithSort(String field);

}

