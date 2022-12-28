package com.ubi.academicapplication.mapper;

import java.util.HashSet;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ubi.academicapplication.dto.classdto.ClassDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.RegionDto;
import com.ubi.academicapplication.dto.school.SchoolDto;
import com.ubi.academicapplication.dto.school.SchoolRegionDto;
import com.ubi.academicapplication.entity.ClassDetail;
import com.ubi.academicapplication.entity.Region;
import com.ubi.academicapplication.entity.School;

@Component
public class SchoolMapper {

	@Autowired
	ClassMapper mapper;

	ModelMapper modelMapper = new ModelMapper();

	// entity to DTO Mapping
	public SchoolDto entityToDto(School school) {
		SchoolDto schoolDto = new SchoolDto();
		schoolDto.setSchoolId(school.getSchoolId());
		schoolDto.setCode(school.getCode());
		schoolDto.setName(school.getName());
		schoolDto.setEmail(school.getEmail());
		schoolDto.setContact(school.getContact());
		schoolDto.setAddress(school.getAddress());
		schoolDto.setType(school.getType());
		schoolDto.setStrength(school.getStrength());
		schoolDto.setShift(school.getShift());
		schoolDto.setExemptionFlag(school.isExemptionFlag());
		schoolDto.setVvnAccount(school.getVvnAccount());
		schoolDto.setVvnFund(school.getVvnFund());
		schoolDto.setRegionId(school.getRegion().getId());

		if(school.getClassDetail() != null) {
			for (ClassDetail cd : school.getClassDetail()) {
				schoolDto.setClassId(new HashSet<>());
				if(cd != null) schoolDto.getClassId().add(cd.getClassId());
			}
		}

		return schoolDto;
	}

	public Set<SchoolDto> entitiesToDtos(Set<School> school) {
		return school.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toSet());
	}

	public List<SchoolDto> entitiesToDtos(List<School> school) {
		return school.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toList());
	}

	public ClassDto entityToDto(ClassDetail classDetail) {
		return modelMapper.map(classDetail, ClassDto.class);
	}

	public SchoolDto entityToDtos(School school) {
		SchoolDto schoolDto = modelMapper.map(school, SchoolDto.class);
		if (school.getRegion() != null) {
			schoolDto.setRegionId(school.getRegion().getId());
		}
		Set<Long> classId = school.getClassDetail().stream().map(classD -> classD.getClassId())
				.collect(Collectors.toSet());
		schoolDto.setClassId(classId);
		return schoolDto;
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

	public SchoolRegionDto toSchoolClassDto(School school) {
		SchoolDto schoolDto = this.entityToDto(school);
		Region region = school.getRegion();
		RegionDto regionDto = new RegionDto();
		regionDto.setCode(region.getCode());
		regionDto.setName(region.getName());
		regionDto.setId(region.getId());
		Set<ClassDto> classDtoSet = new HashSet<>();
		if(school.getClassDetail() != null) {
			for (ClassDetail classDetail : school.getClassDetail()) {
				if (classDetail != null) {
					ClassDto classDetailDto = new ClassDto();
					classDetailDto.setClassCode(classDetail.getClassCode());
					classDetailDto.setClassName(classDetail.getClassName());
					classDetailDto.setClassId(classDetail.getClassId());
					classDtoSet.add(classDetailDto);
				}
			}
		}
		return new SchoolRegionDto(schoolDto, regionDto, classDtoSet);
	}
}
