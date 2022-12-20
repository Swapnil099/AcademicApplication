package com.ubi.academicapplication.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
public class Student {

	@Id
	@GeneratedValue
	private int studentId;
	private String studentName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

	private boolean studentStatus;
	private String category;
	private String minority;
	private String fatherName;
	private String fatherOccupation;
	private String motherName;
	private String motherOccupation;
	private String gender;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate joiningDate;
	private String status;
	private String verifiedByTeacher;
	private String verifiedByPrincipal;
	private String verifiedByRegion;
	private Boolean isActivate;
	private String currentStatus;
	
	
	@ManyToOne(cascade = CascadeType.ALL )
//	@Column(name="class_fId")
	private ClassDetail classDetail;

}
