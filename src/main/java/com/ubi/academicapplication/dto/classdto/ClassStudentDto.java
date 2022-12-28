package com.ubi.academicapplication.dto.classdto;

import java.util.Set;

import com.ubi.academicapplication.dto.school.SchoolDto;
import com.ubi.academicapplication.dto.student.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassStudentDto {
	
	private ClassDto classDto;
	private SchoolDto schoolDto;
	private Set<StudentDto> studentDto;

}
