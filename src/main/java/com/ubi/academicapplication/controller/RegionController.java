package com.ubi.academicapplication.controller;

import java.util.List;

import com.ubi.academicapplication.dto.pagination.PaginationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.ubi.academicapplication.dto.educationaldto.regionDto.RegionCreationDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.RegionDetailsDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.RegionDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.RegionSchoolDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.RegionSchoolMappingDto;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.security.roleaccessinterface.IsEducationInstituteHQAdmin;
import com.ubi.academicapplication.service.RegionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/region")
public class RegionController {

	Logger logger = LoggerFactory.getLogger(RegionController.class);

	@Autowired
	private RegionService regionService;

	@Operation(summary = "Create New Region", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping
	@IsEducationInstituteHQAdmin
	public ResponseEntity<Response<RegionDetailsDto>> insertRegion(@RequestBody RegionCreationDto regionCreationDto) {

		Response<RegionDetailsDto> response = this.regionService.addRegion(regionCreationDto);

		return ResponseEntity.ok().body(response);

	}

	@Operation(summary = "Get Region By Id", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/{id}")
	@IsEducationInstituteHQAdmin
	public ResponseEntity<Response<RegionDetailsDto>> getSingleRegion(@PathVariable int id) {
		Response<RegionDetailsDto> response = regionService.getRegionById(id);
		if (response.getStatusCode() == 200) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@Operation(summary = "Get All Region", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping()
	@IsEducationInstituteHQAdmin
	public ResponseEntity<Response<PaginationResponse<List<RegionDetailsDto>>>> getAllRegions(
			@RequestParam(value = "PageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "PageSize", defaultValue = "5", required = false) Integer pageSize) {
		Response<PaginationResponse<List<RegionDetailsDto>>> response = regionService.getRegionDetails(pageNumber, pageSize);
		return ResponseEntity.ok().body(response);

	}

	@Operation(summary = "Delete Region By Id", security = @SecurityRequirement(name = "bearerAuth"))
	@DeleteMapping("/{id}")
	@IsEducationInstituteHQAdmin
	public ResponseEntity<Response<RegionDto>> deleteRegionById(@PathVariable("id") int id) {

		Response<RegionDto> response = this.regionService.deleteRegionById(id);

		return ResponseEntity.ok().body(response);

	}

	@Operation(summary = "Update Region with Id", security = @SecurityRequirement(name = "bearerAuth"))
	@PutMapping
	public ResponseEntity<Response<RegionDetailsDto>> updateRegion(@RequestBody RegionDto region) { // NOSONAR

		Response<RegionDetailsDto> response = this.regionService.updateRegionDetails(region);

		return ResponseEntity.ok().body(response);

	}

	@Operation(summary = "Download Region and Education Detail file ", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/download")
	@IsEducationInstituteHQAdmin
	public ResponseEntity<Resource> getCSVFileData() {
		String filename = "region.csv";
		InputStreamResource file = new InputStreamResource(regionService.load());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}

	@Operation(summary = "Download file ", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/getcsvdata")
	@IsEducationInstituteHQAdmin
	public ResponseEntity<Resource> getRegionCsvFileData() {
		String filename = "regionschool.csv";
		InputStreamResource file = new InputStreamResource(regionService.Regionload());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}

	@Operation(summary = "Get Region By Region Name", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/region/{name}")
	@IsEducationInstituteHQAdmin
	public ResponseEntity<Response<RegionDto>> getSingleRegion(@RequestParam String name) {
		Response<RegionDto> response = regionService.getRegionByName(name);
		if (response.getStatusCode() == 200) {
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@Operation(summary = "Map Region and School", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping("/addSchool")
	@IsEducationInstituteHQAdmin
	public ResponseEntity<Response<RegionSchoolDto>> addSchool(@RequestBody RegionSchoolMappingDto regionSchoolDto) {
		Response<RegionSchoolDto> response = regionService.addSchool(regionSchoolDto);
		return ResponseEntity.ok().body(response);
	}

	@Operation(summary = "Get School in Region", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/getRegion/{id}")
	@IsEducationInstituteHQAdmin
	public ResponseEntity<Response<RegionSchoolDto>> getSchoolInRegion(@PathVariable int id) {
		Response<RegionSchoolDto> response = regionService.getRegionwithSchool(id);
		return ResponseEntity.ok().body(response);
	}

	// -----Sorting

	@Operation(summary = "Get Region in Sorting", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/sort/{field}")
	@IsEducationInstituteHQAdmin
	public ResponseEntity<Response<List<RegionDetailsDto>>> getRegionBySorting(@PathVariable String field) {
		Response<List<RegionDetailsDto>> response = regionService.getRegionwithSort(field);
		return ResponseEntity.ok().body(response);
	}

}
