package com.ubi.academicapplication.dto.student;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
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
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate joiningDate;
	
	private String status;
	private String verifiedByTeacher;
	private String verifiedByPrincipal;
	private String verifiedByRegion;
}
