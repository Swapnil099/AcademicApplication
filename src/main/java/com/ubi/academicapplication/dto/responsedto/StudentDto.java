package com.ubi.academicapplication.dto.responsedto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
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
