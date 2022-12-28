package com.ubi.academicapplication.dto.school;

import java.util.HashSet;
import java.util.Set;

import com.ubi.academicapplication.dto.classdto.ClassDto;
import com.ubi.academicapplication.entity.ClassDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDetailsDto {
	private int schoolId;

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
	
   Set<ClassDto> classDto=new HashSet<>();
}
