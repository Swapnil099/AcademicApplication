package com.ubi.academicapplication.dto.school;

import java.util.Set;

import com.ubi.academicapplication.dto.classdto.ClassDto;
import com.ubi.academicapplication.dto.educationaldto.regionDto.RegionDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SchoolRegionDto {

	private SchoolDto schoolDto;
	private RegionDto regionDto;
	private Set<ClassDto> classDto;
}
