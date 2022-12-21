package com.ubi.academicapplication.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import com.ubi.academicapplication.dto.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ubi.academicapplication.dto.transfercertificate.TransferCertificateDto;
import com.ubi.academicapplication.service.TransferCertificateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/transferCertificate")
public class TransferCertificateController {

	@Autowired(required = true)
	private TransferCertificateService transferCertificateService;

	Logger logger = LoggerFactory.getLogger(TransferCertificateController.class);


	@Operation(summary = "Create New Transfer Certificate", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping
	public ResponseEntity<Response<TransferCertificateDto>> addTransferCertificate(@Valid @RequestBody TransferCertificateDto transferCertificateDto) {
		Response<TransferCertificateDto> transferCertificateResponse = transferCertificateService.addTransferCertificate(transferCertificateDto);
		return ResponseEntity.ok().body(transferCertificateResponse);

	}

	@Operation(summary = "Get All Transfer Certificate ", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping
	public ResponseEntity<Response<List<TransferCertificateDto>>> getAllTransferCertificate(	
			@RequestParam(value = "PageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "PageSize", defaultValue = "5", required = false) Integer pageSize) {
		Response<List<TransferCertificateDto>> response = transferCertificateService.getAllTransferCertificate(pageNumber, pageSize);
		return ResponseEntity.ok().body(response);

	}
	@Operation(summary = "Delete Transfer Certificate By Id", security = @SecurityRequirement(name = "bearerAuth"))
	@DeleteMapping("/{transferCertificateId}")
	public ResponseEntity<Response<TransferCertificateDto>> deleteTransferCertificate(@PathVariable("transferCertificateId") int transferCertificateId) 
	{
		Response<TransferCertificateDto> response = transferCertificateService.deleteTransferCertificate(transferCertificateId);
		return ResponseEntity.ok().body(response);
	}
	
	@Operation(summary = "Get Single Transfer Certificate", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/{id}")
	public ResponseEntity<Response<TransferCertificateDto>> getSingleTransferCertificate(@PathVariable int id) {
	  Response<TransferCertificateDto> response=transferCertificateService.getSingleTransferCertificate(id);
	  return ResponseEntity.ok().body(response);
	}

	@Operation(summary = "Update Transfer Certificate By Id", security = @SecurityRequirement(name = "bearerAuth"))
	@PutMapping("/{tcId}")
	public ResponseEntity<Response<TransferCertificateDto>> updateTransferCertificate(@Valid @RequestBody TransferCertificateDto transferCertificateDto) throws ParseException { // NOSONAR
	  Response<TransferCertificateDto> response=transferCertificateService.updateTransferCertificate(transferCertificateDto);
	  return ResponseEntity.ok().body(response);
	}

	
	@Operation(summary = "Get Single Transfer Certificate", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/studentId/{studentId}")
	public ResponseEntity<Response<List<TransferCertificateDto>>> getTransferCertificateById(@PathVariable int studentId) {
	  Response<List<TransferCertificateDto>> response=transferCertificateService.getTransferCertificateById(studentId);
	  
	  return ResponseEntity.ok().body(response);
	}
	
}