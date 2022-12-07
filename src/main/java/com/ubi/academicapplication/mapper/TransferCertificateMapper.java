package com.ubi.academicapplication.mapper;

import org.springframework.stereotype.Component;

import com.ubi.academicapplication.dto.responsedto.TransferCertificateDto;
import com.ubi.academicapplication.entity.TransferCertificate;

@Component
public class TransferCertificateMapper {
	public TransferCertificateDto toDto(TransferCertificate tc){
		return new TransferCertificateDto(tc.getId(),tc.getDateOfIssue());
	}

}
