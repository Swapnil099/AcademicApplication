package com.ubi.academicapplication.dto.transfercertificate;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
@AllArgsConstructor
public class TransferCertificateDto {
	private int id;
	private Date dateOfIssue;
}