package com.ubi.academicapplication.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Student {

	@Id
	@GeneratedValue
	private int studentId;
	private String studentName;
	private boolean studentStatus;
	private String category;
	private String fatherName;
	private String fatherOccupation;
	private String motherName;
	private String motherOccupation;
	private String gender;
	private Date joiningDate;
	private String status;
	private String verifiedByTeacher;
	private String verifiedByPrincipal;
	private String verifiedByRegion;
	

	
}
