package com.ubi.academicapplication.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ubi.academicapplication.dto.schooldto.SchoolDto;
import com.ubi.academicapplication.dto.student.StudentDto;
import com.ubi.academicapplication.entity.School;
import com.ubi.academicapplication.entity.Student;

@Component
public class SchoolMapper {

	
	
	 ModelMapper modelMapper= new ModelMapper();
	 
		//entity to DTO Mapping
	 public SchoolDto entityToDto(School school) {
			return modelMapper.map(school, SchoolDto.class);
		}
	 
	 public List<SchoolDto> entitiesToDtos(List<School> school) {
	        return school.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toList());
	    }
	 
	 

		// DTO to entity Mapping
		public School dtoToEntity(SchoolDto schoolDto) {
			return modelMapper.map(schoolDto, School.class);
		}
		
	    public List<School> dtosToEntities(List<SchoolDto> schoolDTOs) {
	        return schoolDTOs.stream().filter(Objects::nonNull).map(this::dtoToEntity).collect(Collectors.toList());
	    }
	
	
	
}
