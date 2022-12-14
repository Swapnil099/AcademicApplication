package com.ubi.academicapplication.dto.educationaldto.regionDto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionCreationDto {
	private String code;
	private String name;
	private Set<Integer> eduInstId;
}
