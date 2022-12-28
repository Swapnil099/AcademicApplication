package com.ubi.academicapplication.dto.educationaldto.regionDto;

import java.util.Set;

import com.ubi.academicapplication.dto.educationaldto.EducationalInstitutionDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationRegionGetDto {

	private EducationalInstitutionDto educationalInstituteDto;
	
	private Set<RegionGet> regionDto;
	
	//private Integer totalEducationInstituteCount;
}
