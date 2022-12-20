package com.ubi.academicapplication.mapper;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.ubi.academicapplication.dto.school.SchoolDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.ubi.academicapplication.entity.School;

@Component
public class SchoolMapper {

	
	
	 ModelMapper modelMapper= new ModelMapper();
	 
		//entity to DTO Mapping
	 public SchoolDto entityToDto(School school) {
			return modelMapper.map(school, SchoolDto.class);
		}
	 
	 public Set<SchoolDto> entitiesToDtos(Set<School> school) {
	        return school.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toSet());
	    }
	 
	 

		// DTO to entity Mapping
		public School dtoToEntity(SchoolDto schoolDto) {
			return modelMapper.map(schoolDto, School.class);
		}
		
	    public List<School> dtosToEntities(List<SchoolDto> schoolDTOs) {
	        return schoolDTOs.stream().filter(Objects::nonNull).map(this::dtoToEntity).collect(Collectors.toList());
	    }

		public List<SchoolDto> entitiesToDtos(List<School> list) {
			 return list.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toList());
		}
	
	
	
}
