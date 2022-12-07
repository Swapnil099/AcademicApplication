package com.ubi.academicapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.entity.TransferCertificate;
import com.ubi.academicapplication.repository.TransferCertificateRepository;
import com.ubi.academicapplication.service.TransferCertificateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("TransferCertificate/")
public class TransferCertificateController {
	
	    @Autowired(required=true)
		private TransferCertificateService transferCertificateService;
		
	    @Autowired
	    private TransferCertificateRepository transferCertificateRepository;
	    
	    Logger logger = LoggerFactory.getLogger(TransferCertificateController.class);
	    
	   // Save tc into database
	    @Operation(summary = "Create New Transfer Certificate", security = @SecurityRequirement(name = "bearerAuth"))
		@PostMapping
		public  ResponseEntity<TransferCertificate> addTC(@Valid @RequestBody TransferCertificate transferCertificate) { //NOSONAR
		Response<TransferCertificate> tc=	this.transferCertificateService.addTC(transferCertificate);
			return new ResponseEntity<TransferCertificate>(HttpStatus.OK);

		}
	   
		 // Fetch all tc from database
	    @Operation(summary = "Get all Transfer Certificate Details", security = @SecurityRequirement(name = "bearerAuth"))
		@GetMapping
		public ResponseEntity<TransferCertificate> getTC() {
			Response<List<TransferCertificate>> tc=this.transferCertificateService.getTC();
			return new ResponseEntity<TransferCertificate>( HttpStatus.OK);
			 
		}
		
		
		  //Update existing Tc 
	    @Operation(summary = "Update Transfer Certificate By Id", security = @SecurityRequirement(name = "bearerAuth"))
		@PutMapping("/{tcId}")
		public ResponseEntity<TransferCertificate> updateTC(@Valid @RequestBody TransferCertificate transferCertificate,@PathVariable("tcId")int tcId) { //NOSONAR
		TransferCertificate tc= this.transferCertificateService.updateTC(transferCertificate);
		return new ResponseEntity<TransferCertificate>(tc, HttpStatus.OK);
		}
		
		//Delete Tc based on id
	    @Operation(summary = "Delete Transfer Certificate By Id", security = @SecurityRequirement(name = "bearerAuth"))
		@DeleteMapping("/{tcId}")
		public ResponseEntity<HttpStatus>  deleteTC( @RequestBody TransferCertificate newTC,@PathVariable ("tcId")int tcId) //NOSONAR
		{ 
			try
		  {
			transferCertificateService.deleteTC(tcId);
			return new ResponseEntity<>(HttpStatus.OK);
		  }catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	 }
			public TransferCertificateController(TransferCertificateService transferCertificateService) {
			super();
			this.transferCertificateService = transferCertificateService;
		}
		
		
		//Get single Tc
		@Operation(summary = "Get Single Transfer Certificate", security = @SecurityRequirement(name = "bearerAuth"))
		@GetMapping("/SingleTC/{id}")
		public ResponseEntity<TransferCertificate> getSingleTC(@PathVariable int id) {
		TransferCertificate singletc=this.transferCertificateService.getSingleTC(id);
			return new ResponseEntity<TransferCertificate>(singletc, HttpStatus.OK);
		}
		
		
		//pagination
		@Operation(summary = "Pagination", security = @SecurityRequirement(name = "bearerAuth"))
				@GetMapping("{offset}/{pageSize}")
				public  ResponseEntity<Page<TransferCertificate>> getTCWithPagination(@PathVariable int offset, @PathVariable int pageSize){
					Page<TransferCertificate> transferCertificate=this.transferCertificateService.findTCWithPagination(offset, pageSize);
					return new ResponseEntity<Page<TransferCertificate>>(transferCertificate,HttpStatus.OK);
				}
				
				//sort TC based on any field
		@Operation(summary = "Sort Transfer Certificate By any Field ", security = @SecurityRequirement(name = "bearerAuth"))
		        @GetMapping("/{field}")
				public  Iterable<TransferCertificate> getTCWithSort(@PathVariable String field){
					
		        	 return   transferCertificateService.findTCWithSorting(field);
					
				}
		
}

