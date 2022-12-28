package com.ubi.academicapplication.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ubi.academicapplication.dto.classdto.ClassDto;
import com.ubi.academicapplication.entity.ClassDetail;

@Component
public class ClassMapper {
	ModelMapper modelMapper= new ModelMapper();
	 
	//entity to DTO Mapping
 public ClassDto entityToDto(ClassDetail classDetail) {
	 	ClassDto classDto=new ClassDto();
	 	classDto.setClassId(classDetail.getClassId());
	 	classDto.setClassName(classDetail.getClassName());
	 	classDto.setClassCode(classDetail.getClassCode());
		return classDto;
	}
 
 public List<ClassDto> entitiesToDtos(List<ClassDetail> classDetail) {
        return classDetail.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toList());
    }
 
 public Set<ClassDto> entitiesToDto(Set<ClassDetail> classDetail) {
		return classDetail.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toSet());
	}

	// DTO to entity Mapping
	public ClassDetail dtoToEntity(ClassDto classDto) {
		return modelMapper.map(classDto, ClassDetail.class);
	}
	
    public List<ClassDetail> dtosToEntities(List<ClassDto> classDtos) {
        return classDtos.stream().filter(Objects::nonNull).map(this::dtoToEntity).collect(Collectors.toList());
    }
      
}
