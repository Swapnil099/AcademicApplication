package com.ubi.academicapplication.dto.regionDto;

import java.util.Set;

import com.ubi.academicapplication.entity.EducationalInstitution;
import com.ubi.academicapplication.entity.Region;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationalRegionDto {
	private EducationalInstitution educationalInstitute;
	private Set<Region> regions;
}
