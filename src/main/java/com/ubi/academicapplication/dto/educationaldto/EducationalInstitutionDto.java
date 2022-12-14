package com.ubi.academicapplication.dto.educationaldto;

import java.util.Set;

import com.ubi.academicapplication.entity.Region;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EducationalInstitutionDto {

	private int id;

	private String educationalInstitutionCode;

	private String educationalInstitutionName;

	private String educationalInstitutionType;

	private Long strength;

	private String state;

	private String exemptionFlag;

	private Long vvnAccount;

	private Set<Integer> regionId;
	

}
