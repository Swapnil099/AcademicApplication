package com.ubi.academicapplication.dto.student;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StudentVerifyDto {
	
	@Id
	@GeneratedValue
	
	private Set<Long> studentId;

	

}
