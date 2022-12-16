package com.ubi.academicapplication.dto.regionDto;

import java.util.Set;

import com.ubi.academicapplication.dto.educationaldto.EducationalInstitutionDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationalRegionDto {
	private EducationalInstitutionDto educationalInstituteDto; 
	private Set<RegionDto> regionDto;
}
