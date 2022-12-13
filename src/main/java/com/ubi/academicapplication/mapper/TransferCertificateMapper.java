package com.ubi.academicapplication.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


import com.ubi.academicapplication.dto.transfercertificate.TransferCertificateDto;

import com.ubi.academicapplication.entity.TransferCertificate;

@Component
public class TransferCertificateMapper {
	
	 ModelMapper modelMapper= new ModelMapper();
	 
		//entity to DTO Mapping
	 public TransferCertificateDto entityToDto(TransferCertificate transferCertificate) {
			return modelMapper.map(transferCertificate, TransferCertificateDto.class);
		}
	 
	 public List<TransferCertificateDto> entitiesToDtos(List<TransferCertificate> transferCertificate) {
	        return transferCertificate.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toList());
	    }
	 
	 

		// DTO to entity Mapping
		public TransferCertificate dtoToEntity(TransferCertificateDto transferCertificateDto) {
			return modelMapper.map(transferCertificateDto, TransferCertificate.class);
		}
		
	    public List<TransferCertificate> dtosToEntities(List<TransferCertificateDto> transferCertificateDTOs) {
	        return transferCertificateDTOs.stream().filter(Objects::nonNull).map(this::dtoToEntity).collect(Collectors.toList());
	    }
	 
}
