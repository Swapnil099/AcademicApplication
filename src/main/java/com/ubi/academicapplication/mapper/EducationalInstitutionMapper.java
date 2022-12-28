package com.ubi.academicapplication.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ubi.academicapplication.dto.educationaldto.EducationalInstitutionDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.EducationRegionGetDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.EducationalRegionDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.RegionDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.RegionGet;
import com.ubi.academicapplication.entity.EducationalInstitution;
import com.ubi.academicapplication.entity.Region;
import com.ubi.academicapplication.repository.RegionRepository;

@Component
public class EducationalInstitutionMapper {
	
	ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	RegionRepository regionRepository;

	public EducationalInstitutionDto entityToDto(EducationalInstitution educationalInstitution) {
		EducationalInstitutionDto educationalInstitutionDto = modelMapper.map(educationalInstitution, EducationalInstitutionDto.class);
		Set<Integer> regionId = educationalInstitution.getRegion().stream().filter(Objects::nonNull).map(region -> region.getId()).collect(Collectors.toSet());
		educationalInstitutionDto.setRegionId(regionId);
		return educationalInstitutionDto;
	}

	public List<EducationalInstitutionDto> entitiesToDtos(List<EducationalInstitution> educationalInstitution) {
		return educationalInstitution.stream().filter(Objects::nonNull).map(this::entityToDto)
				.collect(Collectors.toList());
	}
	
	public RegionDto entityToDto(Region region) {
		return modelMapper.map(region, RegionDto.class);
	}
	
	public Set<RegionDto> entitiesToDto(Set<Region> region) {
		return region.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toSet());
	}

	// DTO to entity Mapping
	public EducationalInstitution dtoToEntity(EducationalInstitutionDto educationalInstitutionDto) {
		EducationalInstitution educationalInstitution = modelMapper.map(educationalInstitutionDto, EducationalInstitution.class);
		for(Integer regionId:educationalInstitutionDto.getRegionId()) {
			Region region = regionRepository.getReferenceById(regionId);
			educationalInstitution.getRegion().add(region);
		}
		return educationalInstitution;
	}

	public List<EducationalInstitution> dtosToEntities(List<EducationalInstitutionDto> educationalInstitutionDtos) {
		return educationalInstitutionDtos.stream().filter(Objects::nonNull).map(this::dtoToEntity)
				.collect(Collectors.toList());
	}
	
	public EducationalRegionDto toEducationalRegionDto(EducationalInstitution educationalInstitute)
	{
		EducationalInstitutionDto educationalInstitutionDto = this.entityToDto(educationalInstitute);
		
		Set<RegionDto> regionDtoSet = new HashSet<>();
		for(Region region:educationalInstitute.getRegion()) {
			if(region != null) {
				RegionDto regionDto =  new RegionDto();
				regionDto.setCode(region.getCode());
				regionDto.setName(region.getName());
				regionDto.setId(region.getId());
				regionDto.setSchoollId(region.getSchool().stream().map(school->school.getSchoolId()).collect(Collectors.toSet()));
				regionDto.setEduInstId(region.getEducationalInstitiute().stream().map(eduInsti->eduInsti.getId()).collect(Collectors.toSet()));
				regionDtoSet.add(regionDto);
			}
		}
		
		return new EducationalRegionDto(educationalInstitutionDto,regionDtoSet);
	}
	
	
	
	
	//-------------
	
	
	public EducationalInstitutionDto entityToDtos(EducationalInstitution educationalInstitution) {
		EducationalInstitutionDto educationalInstitutionDto = modelMapper.map(educationalInstitution, EducationalInstitutionDto.class);
		Set<Integer> regionId = educationalInstitution.getRegion().stream().map(region -> region.getId()).collect(Collectors.toSet());
		educationalInstitutionDto.setRegionId(regionId);
		return educationalInstitutionDto;
	}
	
	public EducationRegionGetDto toEducationalRegionDtos(EducationalInstitution educationalInstitute)
	{
		EducationalInstitutionDto educationalInstitutionDto = this.entityToDtos(educationalInstitute);
		
		Set<RegionGet> regionDtoSet = new HashSet<>();
		for(Region region:educationalInstitute.getRegion()) {
			RegionGet regionDto =  new RegionGet();
			regionDto.setCode(region.getCode());
			regionDto.setName(region.getName());
			regionDto.setId(region.getId());
			regionDtoSet.add(regionDto);
		}
		
		return new EducationRegionGetDto(educationalInstitutionDto,regionDtoSet);
	}
		

}
