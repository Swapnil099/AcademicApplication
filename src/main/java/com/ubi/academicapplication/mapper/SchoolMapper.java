package com.ubi.academicapplication.mapper;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.ubi.academicapplication.dto.classdto.ClassDto;
import com.ubi.academicapplication.dto.classdto.SchoolClassDto;
import com.ubi.academicapplication.dto.school.SchoolDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ubi.academicapplication.entity.ClassDetail;
import com.ubi.academicapplication.entity.School;

@Component
public class SchoolMapper {

	@Autowired
	ClassMapper mapper;

	ModelMapper modelMapper = new ModelMapper();

	// entity to DTO Mapping
	public SchoolDto entityToDto(School school) {
		return modelMapper.map(school, SchoolDto.class);
	}

	public List<SchoolDto> entitiesToDtos(List<School> school) {
		return school.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toList());
	}

	public ClassDto entityToDto(ClassDetail classDetail) {
		return modelMapper.map(classDetail, ClassDto.class);
	}

	public List<ClassDto> entitiesToDto(List<ClassDetail> classDetail) {
		return classDetail.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toList());
	}

	// DTO to entity Mapping
	public School dtoToEntity(SchoolDto schoolDto) {
		return modelMapper.map(schoolDto, School.class);
	}

	public List<School> dtosToEntities(List<SchoolDto> schoolDTOs) {
		return schoolDTOs.stream().filter(Objects::nonNull).map(this::dtoToEntity).collect(Collectors.toList());
	}

	public SchoolClassDto toSchoolClassDto(School school) {
		SchoolDto schoolDto = this.entityToDto(school);
		List<ClassDto> classDto = mapper.entitiesToDto(school.getClassDetail());
		return new SchoolClassDto(schoolDto, classDto);
	}
}
