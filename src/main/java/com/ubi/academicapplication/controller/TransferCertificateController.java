package com.ubi.academicapplication.controller;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.entity.TransferCertificate;
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
	public ResponseEntity<Response<TransferCertificate>> addTransferCertificate(@Valid @RequestBody TransferCertificate transferCertificate) {
		Response<TransferCertificate> transferCertificateResponse = transferCertificateService.addTransferCertificate(transferCertificate);
		return ResponseEntity.ok().body(transferCertificateResponse);

	}

	@Operation(summary = "Get all Transfer Certificate Details", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping
	public ResponseEntity<Response<List<TransferCertificate>>> getAllTransferCertificate() {
		Response<List<TransferCertificate>> transferCertificateResponse = transferCertificateService.getAllTransferCertificate();
		return ResponseEntity.ok().body(transferCertificateResponse);

	}
	
	@Operation(summary = "Delete Transfer Certificate By Id", security = @SecurityRequirement(name = "bearerAuth"))
	@DeleteMapping("/{transferCertificateId}")
	public ResponseEntity<Response<TransferCertificate>> deleteTransferCertificate(@PathVariable("transferCertificateId") int transferCertificateId) 
	{
		Response<TransferCertificate> response = transferCertificateService.deleteTransferCertificate(transferCertificateId);
		return ResponseEntity.ok().body(response);
	}
	
	@Operation(summary = "Get Single Transfer Certificate", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/{id}")
	public ResponseEntity<Response<TransferCertificate>> getSingleTransferCertificate(@PathVariable int id) {
	  Response<TransferCertificate> response=transferCertificateService.getSingleTransferCertificate(id);
	  return ResponseEntity.ok().body(response);
	}

	@Operation(summary = "Update Transfer Certificate By Id", security = @SecurityRequirement(name = "bearerAuth"))
	@PutMapping("/{tcId}")
	public ResponseEntity<Response<TransferCertificate>> updateTransferCertificate(@Valid @RequestBody TransferCertificate transferCertificate) throws ParseException { // NOSONAR
	  Response<TransferCertificate> response=transferCertificateService.updateTransferCertificate(transferCertificate);
	  return ResponseEntity.ok().body(response);
	}
//	
//	// pagination
//	@Operation(summary = "Pagination", security = @SecurityRequirement(name = "bearerAuth"))
//	@GetMapping("{offset}/{pageSize}")
//	public ResponseEntity<Page<TransferCertificate>> getTCWithPagination(@PathVariable int offset,
//			@PathVariable int pageSize) {
//		Page<TransferCertificate> transferCertificate = this.transferCertificateService.findTCWithPagination(offset,
//				pageSize);
//		return new ResponseEntity<Page<TransferCertificate>>(transferCertificate, HttpStatus.OK);
//	}
//
//	@Operation(summary = "Sort Transfer Certificate By any Field ", security = @SecurityRequirement(name = "bearerAuth"))
//	@GetMapping("/{field}")
//	public Iterable<TransferCertificate> getTCWithSort(@PathVariable String field) {
//
//		return transferCertificateService.findTCWithSorting(field);
//
//	}

}
