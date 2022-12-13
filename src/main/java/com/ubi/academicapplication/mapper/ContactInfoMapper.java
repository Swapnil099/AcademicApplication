package com.ubi.academicapplication.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ubi.academicapplication.dto.contactinfodto.ContactInfoDto;
import com.ubi.academicapplication.entity.ContactInfo;


@Component
public class ContactInfoMapper {
	
	ModelMapper modelMapper= new ModelMapper();
	 
	//entity to DTO Mapping
 public ContactInfoDto entityToDto(ContactInfo contactInfo) {
		return modelMapper.map(contactInfo, ContactInfoDto.class);
	}
 
 public List<ContactInfoDto> entitiesToDtos(List<ContactInfo> contactInfo) {
        return contactInfo.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toList());
    }

	// DTO to entity Mapping
	public ContactInfo dtoToEntity(ContactInfoDto contactInfoDto) {
		return modelMapper.map(contactInfoDto, ContactInfo.class);
	}
	
    public List<ContactInfo> dtosToEntities(List<ContactInfoDto> contactInfoDtos) {
        return contactInfoDtos.stream().filter(Objects::nonNull).map(this::dtoToEntity).collect(Collectors.toList());
    }

}
