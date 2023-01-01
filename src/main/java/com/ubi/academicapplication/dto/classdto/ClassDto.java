package com.ubi.academicapplication.dto.classdto;

import java.util.Set;

import com.ubi.academicapplication.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDto {
	private Long classId;
	private String classCode;
	private String className;
	private int schoolId;
}
