package com.ubi.academicapplication.dto.school;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolCreationDto {

	private int code;

	private String name;

	private String email;

	private long contact;

	private String address;

	private String type;

	private int strength;

	private String shift;

	private boolean exemptionFlag;

	private int vvnAccount;

	private int vvnFund;
	
	private int regionId;
	
	private Set<Long> classId;
	
	private int educationalInstitutionId;
	
	
	
}
