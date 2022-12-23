package com.ubi.academicapplication.dto.regionDto;

import java.util.Set;

import com.ubi.academicapplication.dto.educationaldto.EducationalInstitutionDto;
import com.ubi.academicapplication.dto.school.SchoolDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RegionSchoolDto {
		private RegionDto regionDto; 
		private Set<SchoolDto> schoolDto;
	}

