package com.ubi.academicapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.ubi.academicapplication.dto.contactinfodto.ContactInfoDto;
import com.ubi.academicapplication.dto.regionDto.RegionDto;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.service.ContactInfoServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/ContactInfo")
public class ContactInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactInfoController.class);

	@Autowired
	private ContactInfoServiceImpl contactInfoServiceImpl;


	@PostMapping
	@Operation(summary = "Add Contact Details", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Response<ContactInfoDto>> addContactInfo(@Valid @RequestBody ContactInfoDto contactInfoDto) {
		Response<ContactInfoDto> response = contactInfoServiceImpl.addContactInfo(contactInfoDto);
		return ResponseEntity.ok().body(response);
	}

	@GetMapping
	@Operation(summary = "Get All Contact details", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Response<List<ContactInfoDto>>> getContactInfo(	
			@RequestParam(value = "PageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "PageSize", defaultValue = "5", required = false) Integer pageSize) {
		Response<List<ContactInfoDto>> response = contactInfoServiceImpl.getContactInfo(pageNumber, pageSize);
		return ResponseEntity.ok().body(response);

	}

	@GetMapping("{classid}")
	@Operation(summary = "Get Contact Info By Id", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Response> getContactInfoById(@PathVariable("classid") Long classidL){
		Response response = contactInfoServiceImpl.getContactInfoById(classidL);
		if(response.getStatusCode()==200){
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); 
		}
	}
	
    @DeleteMapping("{classid}")
	@Operation(summary = "Delete Contact By Id", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Response<ContactInfoDto>> deleteContactById(@PathVariable Long classid) {
		Response<ContactInfoDto> response = contactInfoServiceImpl.deleteContactById(classid);
		return ResponseEntity.ok().body(response);

	}
    
    @PutMapping
	@Operation(summary = "Update Contact Details", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<Response> updateClassDetails(@Valid @RequestBody ContactInfoDto contactInfoDto) {
		Response<ContactInfoDto> response = contactInfoServiceImpl.updateContactDetails(contactInfoDto);
		return ResponseEntity.ok().body(response);
	}
    
    @Operation(summary = "Get Contact Info in Sorting", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/sort/{field}")
	public ResponseEntity<Response<List<ContactInfoDto>>> getRegionBySorting(@PathVariable String field) {
		Response<List<ContactInfoDto>> response = contactInfoServiceImpl.getContactInfowithSort(field);
		return ResponseEntity.ok().body(response);
	}


}
