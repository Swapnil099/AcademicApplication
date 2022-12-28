package com.ubi.academicapplication.dto.classdto;

import java.util.HashSet;
import java.util.Set;

import com.ubi.academicapplication.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClassDetailsDto {

	private Long classId;
	private String classCode;
	private String className;
	private int schoolId;
	Set<Student> student = new HashSet<>();
}
