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

import com.ubi.academicapplication.csv.EducationalInstitutionCsvHelper;
import com.ubi.academicapplication.dto.educationaldto.EducationalInstitutionDto;
import com.ubi.academicapplication.dto.regionDto.EIRegionMappingDto;
import com.ubi.academicapplication.dto.regionDto.EducationRegionGetDto;
import com.ubi.academicapplication.dto.regionDto.EducationalRegionDto;
import com.ubi.academicapplication.dto.regionDto.RegionGet;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.entity.EducationalInstitution;
import com.ubi.academicapplication.entity.Region;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.mapper.EducationalInstitutionMapper;
import com.ubi.academicapplication.mapper.RegionMapper;
import com.ubi.academicapplication.repository.EducationalInstitutionRepository;
import com.ubi.academicapplication.repository.RegionRepository;

@Service
public class EducationalInstitutionServiceImpl implements EducationalInstitutionService {

	@Autowired
	private EducationalInstitutionRepository educationalInstitutionRepository;

	@Autowired
	private EducationalInstitutionMapper educationalInstitutionMapper;

	@Autowired
	private RegionMapper regionMapper;

	@Autowired
	private RegionRepository regionRepository;

	Logger logger = LoggerFactory.getLogger(EducationalInstitutionServiceImpl.class);

	@Override
	public Response<EducationalRegionDto> addEducationalInstitution(
			EducationalInstitutionDto educationalInstitutionDto) {

		Result<EducationalRegionDto> res = new Result<>();

		Response<EducationalRegionDto> response = new Response<>();
		Optional<EducationalInstitution> tempeducationalInstitution = educationalInstitutionRepository
				.findById(educationalInstitutionDto.getId());

		EducationalInstitution educationalInstitutionName = educationalInstitutionRepository
				.getEducationalInstitutionByeducationalInstitutionName(
						educationalInstitutionDto.getEducationalInstitutionName());

		EducationalInstitution educationalInstitutionCode = educationalInstitutionRepository
				.getEducationalInstitutionByeducationalInstitutionCode(
						educationalInstitutionDto.getEducationalInstitutionCode());

		if (tempeducationalInstitution.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_FOUND.getCode(),
					HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_FOUND,
					HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_FOUND.getMessage(), res);
		}

		if (educationalInstitutionName != null) {
			throw new CustomException(HttpStatusCode.EDUCATIONAL_INSTITUTION_NAME_ALREADY_EXISTS.getCode(),
					HttpStatusCode.EDUCATIONAL_INSTITUTION_NAME_ALREADY_EXISTS,
					HttpStatusCode.EDUCATIONAL_INSTITUTION_NAME_ALREADY_EXISTS.getMessage(), res);
		}

		if (educationalInstitutionCode != null) {
			throw new CustomException(HttpStatusCode.EDUCATIONAL_INSTITUTION_CODE_ALREADY_EXISTS.getCode(),
					HttpStatusCode.EDUCATIONAL_INSTITUTION_CODE_ALREADY_EXISTS,
					HttpStatusCode.EDUCATIONAL_INSTITUTION_CODE_ALREADY_EXISTS.getMessage(), res);
		}

		EducationalInstitution educationalInstitution = new EducationalInstitution();
		educationalInstitution.setId(educationalInstitutionDto.getId());
		educationalInstitution.setEducationalInstitutionCode(educationalInstitutionDto.getEducationalInstitutionCode());
		educationalInstitution.setEducationalInstitutionName(educationalInstitutionDto.getEducationalInstitutionName());
		educationalInstitution.setEducationalInstitutionType(educationalInstitutionDto.getEducationalInstitutionType());
		educationalInstitution.setState(educationalInstitutionDto.getState());
		educationalInstitution.setExemptionFlag(educationalInstitutionDto.getExemptionFlag());
		educationalInstitution.setStrength(educationalInstitutionDto.getStrength());
		educationalInstitution.setVvnAccount(educationalInstitutionDto.getVvnAccount());
		educationalInstitution.setRegion(new HashSet<>());

		for (Integer regionId : educationalInstitutionDto.getRegionId()) {
			Region region = regionRepository.getReferenceById(regionId);
			System.out.println("region --- " + region.toString());
			if (region != null)
				educationalInstitution.getRegion().add(region);

		}

		if (educationalInstitution.getRegion().isEmpty()) {
			throw new CustomException(HttpStatusCode.NO_REGION_ADDED.getCode(), HttpStatusCode.NO_REGION_ADDED,
					HttpStatusCode.NO_REGION_ADDED.getMessage(), res);
		}

		EducationalInstitution savedEducationalInstitution = educationalInstitutionRepository
				.save(educationalInstitution);

		EducationalRegionDto educationalRegionDto = educationalInstitutionMapper
				.toEducationalRegionDto(savedEducationalInstitution);

		response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
		response.setResult(new Result<EducationalRegionDto>(educationalRegionDto));
		return response;

	}

	@Override
	public Response<EducationalInstitutionDto> getSingleEducationalInstitution(int id) {

		Response<EducationalInstitutionDto> getEducationalInstitution = new Response<>();
		Optional<EducationalInstitution> educationalInst = this.educationalInstitutionRepository.findById(id);
		Result<EducationalInstitutionDto> educationalResult = new Result<>();
		if (!educationalInst.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_MATCH_WITH_ID.getCode(),
					HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_MATCH_WITH_ID,
					HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_MATCH_WITH_ID.getMessage(), educationalResult);
		}
		educationalResult.setData(educationalInstitutionMapper.entityToDto(educationalInst.get()));
		getEducationalInstitution.setStatusCode(HttpStatusCode.EDUCATIONAL_INSTITUTION_RETRIVED_SUCCESSFULLY.getCode());
		getEducationalInstitution.setMessage(HttpStatusCode.EDUCATIONAL_INSTITUTION_RETRIVED_SUCCESSFULLY.getMessage());
		getEducationalInstitution.setResult(educationalResult);
		return getEducationalInstitution;
	}

	@Override
	public Response<EducationalInstitutionDto> getEducationalInstituteByName(String educationalInstitutionName) {

		Result<EducationalInstitutionDto> res = new Result<>();
		res.setData(null);
		Response<EducationalInstitutionDto> getEducationalInstitutionName = new Response<>();
		Optional<EducationalInstitution> educationalInst = this.educationalInstitutionRepository
				.findByeducationalInstitutionName(educationalInstitutionName);
		Result<EducationalInstitutionDto> educationalInstitutionResult = new Result<>();
		if (!educationalInst.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_NAME_FOUND.getCode(),
					HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_NAME_FOUND,
					HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_NAME_FOUND.getMessage(), res);
		}
		educationalInstitutionResult.setData(educationalInstitutionMapper.entityToDto(educationalInst.get()));
		getEducationalInstitutionName
				.setStatusCode(HttpStatusCode.EDUCATIONAL_INSTITUTION_RETRIVED_SUCCESSFULLY.getCode());
		getEducationalInstitutionName
				.setMessage(HttpStatusCode.EDUCATIONAL_INSTITUTION_RETRIVED_SUCCESSFULLY.getMessage());
		getEducationalInstitutionName.setResult(educationalInstitutionResult);
		return getEducationalInstitutionName;
	}

	@Override
	public Response<List<EducationRegionGetDto>> getAllEducationalInstitutions(Integer pageNumber, Integer pageSize) {

		Result<List<EducationRegionGetDto>> allEducationalResult = new Result<>();
		Pageable paging = PageRequest.of(pageNumber, pageSize);
		Response<List<EducationRegionGetDto>> getListofEducationalInstitution = new Response<>();
		
		//Integer count = educationalInstitutionRepository.findAll().size();

		Page<EducationalInstitution> list = this.educationalInstitutionRepository.findAll(paging);

		List<EducationRegionGetDto> EducationalRegionDtoList = new ArrayList<>();
		for (EducationalInstitution eduInsti : list) {
			EducationRegionGetDto educationalRegionDto = new EducationRegionGetDto();
		//	educationalRegionDto.setTotalEducationInstituteCount(count);
			educationalRegionDto.setEducationalInstituteDto(educationalInstitutionMapper.entityToDtos(eduInsti));
			Set<RegionGet> regionDtos = eduInsti.getRegion().stream().map(region -> regionMapper.toDtos(region))
					.collect(Collectors.toSet());
			educationalRegionDto.setRegionDto(regionDtos);
			EducationalRegionDtoList.add(educationalRegionDto);

		}

		if (list.isEmpty()) {
			throw new CustomException(HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_FOUND.getCode(),
					HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_FOUND,
					HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_FOUND.getMessage(), allEducationalResult);
		}
		allEducationalResult.setData(EducationalRegionDtoList);
		getListofEducationalInstitution
				.setStatusCode(HttpStatusCode.EDUCATIONAL_INSTITUTION_RETRIVED_SUCCESSFULLY.getCode());
		getListofEducationalInstitution
				.setMessage(HttpStatusCode.EDUCATIONAL_INSTITUTION_RETRIVED_SUCCESSFULLY.getMessage());
		getListofEducationalInstitution.setResult(allEducationalResult);
		return getListofEducationalInstitution;
	}

	@Override
	public Response<EducationalInstitutionDto> deleteEducationalInstitution(int id) {

		Result<EducationalInstitutionDto> res = new Result<>();
		res.setData(null);
		Optional<EducationalInstitution> educationalInst = educationalInstitutionRepository.findById(id);
		if (!educationalInst.isPresent()) {
			throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(), HttpStatusCode.RESOURCE_NOT_FOUND,
					HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(), res);
		}

		for (Region region : educationalInst.get().getRegion()) {
			region.getEducationalInstitiute().remove(educationalInst.get());
			regionRepository.save(region);
		}

		educationalInst.get().setRegion(new HashSet<>());
		educationalInstitutionRepository.save(educationalInst.get());

		educationalInstitutionRepository.deleteById(id);
		Response<EducationalInstitutionDto> response = new Response<>();
		response.setMessage(HttpStatusCode.EDUCATIONAL_INSTITUTION_DELETED.getMessage());
		response.setStatusCode(HttpStatusCode.EDUCATIONAL_INSTITUTION_DELETED.getCode());
		response.setResult(
				new Result<EducationalInstitutionDto>(educationalInstitutionMapper.entityToDto(educationalInst.get())));
		return response;
	}

	@Override
	public Response<EducationalRegionDto> updateEducationalInstitution(
			EducationalInstitutionDto educationalInstitutionDto) {

		Result<EducationalRegionDto> res = new Result<>();

		res.setData(null);
		Optional<EducationalInstitution> existingEducationalContainer = educationalInstitutionRepository
				.findById(educationalInstitutionDto.getId());
		if (!existingEducationalContainer.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_FOUND.getCode(),
					HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_FOUND,
					HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_FOUND.getMessage(), res);
		}
		EducationalInstitutionDto existingEducationalInstitution = educationalInstitutionMapper
				.entityToDto(existingEducationalContainer.get());
		existingEducationalInstitution
				.setEducationalInstitutionCode(educationalInstitutionDto.getEducationalInstitutionCode());
		existingEducationalInstitution
				.setEducationalInstitutionName(educationalInstitutionDto.getEducationalInstitutionName());
		existingEducationalInstitution
				.setEducationalInstitutionType(educationalInstitutionDto.getEducationalInstitutionType());
		existingEducationalInstitution.setExemptionFlag(educationalInstitutionDto.getExemptionFlag());
		existingEducationalInstitution.setState(educationalInstitutionDto.getState());
		existingEducationalInstitution.setStrength(educationalInstitutionDto.getStrength());
		existingEducationalInstitution.setVvnAccount(educationalInstitutionDto.getVvnAccount());
		existingEducationalInstitution.setRegionId(new HashSet<>());

		for(Integer regionId:educationalInstitutionDto.getRegionId()) {
			Region region = regionRepository.getReferenceById(regionId);
			System.out.println("region --- " + region.toString());
			if(region != null) existingEducationalInstitution.getRegionId().add(regionId);

		}
		
		
		if (educationalInstitutionDto.getRegionId().isEmpty()) {
			throw new CustomException(HttpStatusCode.NO_REGION_ADDED.getCode(), HttpStatusCode.NO_REGION_ADDED,
					HttpStatusCode.NO_REGION_ADDED.getMessage(), res);
		}

		EducationalInstitution ei1 = educationalInstitutionMapper.dtoToEntity(existingEducationalInstitution);
		EducationalInstitution updateEducationalInst = educationalInstitutionRepository.save(ei1);
		EducationalRegionDto educationalRegionDto = educationalInstitutionMapper
				.toEducationalRegionDto(updateEducationalInst);
		Response<EducationalRegionDto> response = new Response<>();
		response.setMessage(HttpStatusCode.EDUCATIONAL_INSTITUTION_UPDATED.getMessage());
		response.setStatusCode(HttpStatusCode.EDUCATIONAL_INSTITUTION_UPDATED.getCode());
		response.setResult(new Result<EducationalRegionDto>(educationalRegionDto));
		return response;
	}

	@Override
	public Response<EducationalRegionDto> addRegion(EIRegionMappingDto eIRegionMappingDto) {
		int eduId = eIRegionMappingDto.getEducationalInstitutionId();
		List<Integer> regionIdList = eIRegionMappingDto.getRegionId();
		Response<EducationalRegionDto> response = new Response<>();
		Result<EducationalRegionDto> res = new Result<>();
		EducationalInstitution eduInstitute = educationalInstitutionRepository.getReferenceById(eduId);
		List<Region> regions = regionRepository.findAll();
		Set<Region> setOfRegion = eduInstitute.getRegion();

		for (int i = 0; i < regions.size(); i++) {
			eduInstitute.getRegion().add(regions.get(i));
			regions.get(i).getEducationalInstitiute().add(eduInstitute);
			regionRepository.save(regions.get(i));
		}
		educationalInstitutionRepository.save(eduInstitute);
		EducationalRegionDto educationalRegionDto = educationalInstitutionMapper.toEducationalRegionDto(eduInstitute);
		response.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
		response.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
		response.setResult(new Result<>(educationalRegionDto));
		return response;
	}

	@Override
	public Response<EducationalRegionDto> getEduInstwithRegion(int id) {

		Response<EducationalRegionDto> response = new Response<>();
		Result<EducationalRegionDto> res = new Result<>();

		Optional<EducationalInstitution> educationalInst = this.educationalInstitutionRepository.findById(id);

		if (!educationalInst.isPresent()) {
			throw new CustomException(HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_MATCH_WITH_ID.getCode(),
					HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_MATCH_WITH_ID,
					HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_MATCH_WITH_ID.getMessage(), res);
		}
		EducationalRegionDto educationalRegionDto = new EducationalRegionDto();
		educationalRegionDto
				.setEducationalInstituteDto(educationalInstitutionMapper.entityToDto(educationalInst.get()));
		educationalRegionDto.setRegionDto(regionMapper.entitiesToDto(educationalInst.get().getRegion()));

		res.setData(educationalRegionDto);

		response.setStatusCode(HttpStatusCode.EDUCATIONAL_INSTITUTION_RETRIVED_SUCCESSFULLY.getCode());
		response.setMessage(HttpStatusCode.EDUCATIONAL_INSTITUTION_RETRIVED_SUCCESSFULLY.getMessage());
		response.setResult(new Result<>(educationalRegionDto));
		return response;

	}

	@Override
	public ByteArrayInputStream load() {
		List<EducationalInstitution> eduInst = educationalInstitutionRepository.findAll();
		ByteArrayInputStream out = EducationalInstitutionCsvHelper.educationCSV(eduInst);
		return out;
	}

	@Override
	public Response<List<EducationalInstitutionDto>> getEduInstwithSort(String field) {

		Result<List<EducationalInstitutionDto>> allEducationalResult = new Result<>();

		Response<List<EducationalInstitutionDto>> getListofEducationalInstitution = new Response<>();

		List<EducationalInstitution> list = this.educationalInstitutionRepository
				.findAll(Sort.by(Sort.Direction.ASC, field));
		List<EducationalInstitutionDto> educationalInstitutionDtos = educationalInstitutionMapper.entitiesToDtos(list);

		if (list.size() == 0) {
			throw new CustomException(HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_FOUND.getCode(),
					HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_FOUND,
					HttpStatusCode.NO_EDUCATIONAL_INSTITUTION_FOUND.getMessage(), allEducationalResult);
		}
		allEducationalResult.setData(educationalInstitutionDtos);
		getListofEducationalInstitution
				.setStatusCode(HttpStatusCode.EDUCATIONAL_INSTITUTION_RETRIVED_SUCCESSFULLY.getCode());
		getListofEducationalInstitution
				.setMessage(HttpStatusCode.EDUCATIONAL_INSTITUTION_RETRIVED_SUCCESSFULLY.getMessage());
		getListofEducationalInstitution.setResult(allEducationalResult);
		return getListofEducationalInstitution;
	}

}
