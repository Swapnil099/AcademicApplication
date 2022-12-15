package com.ubi.academicapplication.dto.transfercertificate;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferCertificateDto {
	private int id;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dateOfIssue;
}