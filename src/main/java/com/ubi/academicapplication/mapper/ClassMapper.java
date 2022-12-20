package com.ubi.academicapplication.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ubi.academicapplication.dto.classdto.ClassDetailDto;
import com.ubi.academicapplication.entity.ClassDetail;

@Component
public class ClassMapper {

	@Autowired
	StudentMapper mapper;

	ModelMapper modelMapper = new ModelMapper();
	
	
//	public ClassStudentDto toClassStudentDto(ClassDetail classDetail)
//	{
//		ClassDetailDto classDetailDto = this.entityToDto(classDetail);
//		Set<StudentDto> regionDto=mapper.entitiesToDto(classDetail.getStudents());
//		return new ClassStudentDto(classDetailDto,regionDto);
//	}
	

	// entity to DTO Mapping
	public ClassDetailDto entityToDto(ClassDetail classDetail) {
		return modelMapper.map(classDetail, ClassDetailDto.class);
	}

	public List<ClassDetailDto> entitiesToDtos(List<ClassDetail> classDetail) {
		return classDetail.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toList());
	}

	// DTO to entity Mapping
	public ClassDetail dtoToEntity(ClassDetailDto classDto) {
		return modelMapper.map(classDto, ClassDetail.class);
	}

	public List<ClassDetail> dtosToEntities(List<ClassDetailDto> classDtos) {
		return classDtos.stream().filter(Objects::nonNull).map(this::dtoToEntity).collect(Collectors.toList());
	}

	
	
	
}
