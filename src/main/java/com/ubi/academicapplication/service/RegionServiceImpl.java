package com.ubi.academicapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.dto.paymentdto.PaymentDto;
import com.ubi.academicapplication.dto.regionDto.RegionDto;
import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.Region;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.mapper.RegionMapper;
import com.ubi.academicapplication.repository.RegionRepository;

@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private RegionMapper regionMapper;

	@Override
	public Response<RegionDto> addRegion(RegionDto regionDto) {
		Result<RegionDto> res = new Result<>();
		Response<RegionDto> response = new Response<>();
		Optional<Region> tempRegion = regionRepository.findById(regionDto.getId());
		if (tempRegion.isPresent()) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}
		Region saveRegion = regionRepository.save(regionMapper.dtoToEntity(regionDto));
		response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		response.setResult(new Result<RegionDto>(regionMapper.entityToDto(saveRegion)));
		return response;
	}

	@Override
	public Response<List<RegionDto>> getRegionDetails(Integer PageNumber, Integer PageSize) {
		Result<List<RegionDto>> allRegion = new Result<>();
		Pageable paging = PageRequest.of(PageNumber, PageSize);
		Response<List<RegionDto>> getListofRegion = new Response<List<RegionDto>>();
		
		Page<Region> list = this.regionRepository.findAll(paging);
		List<RegionDto> paymentDtos = regionMapper.entitiesToDtos(list.toList());
		if (list.getSize() == 0) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), allRegion);
		}
		allRegion.setData(paymentDtos);
		getListofRegion.setStatusCode(HttpStatusCode.REGION_RETREIVED_SUCCESSFULLY.getCode());
		getListofRegion.setMessage(HttpStatusCode.REGION_RETREIVED_SUCCESSFULLY.getMessage());
		getListofRegion.setResult(allRegion);
		return getListofRegion;
	}

	@Override
	public Response<RegionDto> getRegionById(int id) {
		Response<RegionDto> getRegion = new Response<RegionDto>();
		Optional<Region> region = null;
		region = this.regionRepository.findById(id);
		Result<RegionDto> regionResult = new Result<>();
		if (!region.isPresent()) {
			throw new CustomException(HttpStatusCode.REGION_NOT_FOUND.getCode(),
					HttpStatusCode.REGION_NOT_FOUND, HttpStatusCode.REGION_NOT_FOUND.getMessage(),
					regionResult);
		}
		regionResult.setData(regionMapper.entityToDto(region.get()));
		getRegion.setStatusCode(HttpStatusCode.REGION_RETREIVED_SUCCESSFULLY.getCode());
		getRegion.setMessage(HttpStatusCode.REGION_RETREIVED_SUCCESSFULLY.getMessage());
		getRegion.setResult(regionResult);
		return getRegion;
	}

	@Override
	public Response<RegionDto> deleteRegionById(int id) {
		Result<RegionDto> res = new Result<>();
		res.setData(null);
		Optional<Region> region = regionRepository.findById(id);
		if (!region.isPresent()) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}
		regionRepository.deleteById(id);
		Response<RegionDto> response = new Response<>();
		response.setMessage(HttpStatusCode.REGION_DELETED_SUCCESSFULLY.getMessage());
		response.setStatusCode(HttpStatusCode.REGION_DELETED_SUCCESSFULLY.getCode());
		response.setResult(new Result<RegionDto>(regionMapper.entityToDto(region.get())));
		return response;
	}

	@Override
	public Response<RegionDto> updateRegionDetails(RegionDto regionDto) {
		Result<PaymentDto> res = new Result<>();

		res.setData(null);
		Optional<Region> existingRegionContainer = regionRepository.findById(regionDto.getId());
		if (!existingRegionContainer.isPresent()) {
			throw new CustomException(HttpStatusCode.REGION_NOT_FOUND.getCode(), HttpStatusCode.REGION_NOT_FOUND,
					HttpStatusCode.REGION_NOT_FOUND.getMessage(), res);
		}
		RegionDto existingRegion = regionMapper.entityToDto(existingRegionContainer.get());
		existingRegion.setCode(regionDto.getCode());
		existingRegion.setName(regionDto.getName());

		Region updateRegion = regionRepository.save(regionMapper.dtoToEntity(existingRegion));
		Response<RegionDto> response = new Response<>();
		response.setMessage(HttpStatusCode.REGION_UPDATED.getMessage());
		response.setStatusCode(HttpStatusCode.REGION_UPDATED.getCode());
		response.setResult(new Result<>(regionMapper.entityToDto(updateRegion)));
		return response;
	}

}
