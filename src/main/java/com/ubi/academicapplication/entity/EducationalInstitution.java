package com.ubi.academicapplication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EducationalInstitution {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String educationalInstitutionCode;
	
	private String educationalInstitutionName;
	
	private String educationalInstitutionType;
	
	private Long strength;
	
	private String state;
	
	private String exemptionFlag;
	
	private Long vvnAccount;
	
	
	

}
