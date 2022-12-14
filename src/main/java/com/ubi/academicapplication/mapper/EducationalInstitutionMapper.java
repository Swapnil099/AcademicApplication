package com.ubi.academicapplication.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ubi.academicapplication.dto.educationaldto.EducationalInstitutionDto;
import com.ubi.academicapplication.dto.regionDto.EducationalRegionDto;
import com.ubi.academicapplication.entity.EducationalInstitution;
import com.ubi.academicapplication.entity.Region;

@Component
public class EducationalInstitutionMapper {

	ModelMapper modelMapper = new ModelMapper();

	public EducationalInstitutionDto entityToDto(EducationalInstitution educationalInstitution) {
		return modelMapper.map(educationalInstitution, EducationalInstitutionDto.class);
	}

	public List<EducationalInstitutionDto> entitiesToDtos(List<EducationalInstitution> educationalInstitution) {
		return educationalInstitution.stream().filter(Objects::nonNull).map(this::entityToDto)
				.collect(Collectors.toList());
	}

	// DTO to entity Mapping
	public EducationalInstitution dtoToEntity(EducationalInstitutionDto educationalInstitutionDto) {
		return modelMapper.map(educationalInstitutionDto, EducationalInstitution.class);
	}

	public List<EducationalInstitution> dtosToEntities(List<EducationalInstitutionDto> educationalInstitutionDtos) {
		return educationalInstitutionDtos.stream().filter(Objects::nonNull).map(this::dtoToEntity)
				.collect(Collectors.toList());
	}
	
	public EducationalRegionDto toEducationalRegionDto(EducationalInstitution educationalInstitute)
	{
		return new EducationalRegionDto(educationalInstitute,educationalInstitute.getRegion());
	}
	
	

}
