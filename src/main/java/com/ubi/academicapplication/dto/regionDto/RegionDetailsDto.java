package com.ubi.academicapplication.dto.regionDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ubi.academicapplication.dto.educationaldto.EducationalInstitutionDto;
import com.ubi.academicapplication.dto.school.SchoolDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionDetailsDto {
	private int id;
	private String code;
	private String name;
	
	Set<EducationalInstitutionDto> eduInstiDto = new HashSet<>();
	Set<SchoolDto> schoolDto = new HashSet<>();
}
