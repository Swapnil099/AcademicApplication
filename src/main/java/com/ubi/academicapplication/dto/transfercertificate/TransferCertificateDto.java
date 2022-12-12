package com.ubi.academicapplication.dto.transfercertificate;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferCertificateDto {
	private int id;
	private LocalDate dateOfIssue;
}