package com.ubi.academicapplication.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.ubi.academicapplication.dto.regionDto.RegionDto;
import com.ubi.academicapplication.dto.response.Response;

public interface RegionService {

	Response<RegionDto> addRegion(RegionDto regionDto);

	Response<List<RegionDto>> getRegionDetails(Integer PageNumber, Integer PageSize);

	public Response<RegionDto> getRegionById(int id);

	public Response<RegionDto> deleteRegionById(int id);

	Response<RegionDto> updateRegionDetails(RegionDto regionDto);
	
	ByteArrayInputStream load();
	
	Response<RegionDto> getRegionByName(String name);
}

