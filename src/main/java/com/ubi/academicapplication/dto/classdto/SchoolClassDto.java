package com.ubi.academicapplication.dto.classdto;

import java.util.List;
import java.util.Set;

import com.ubi.academicapplication.dto.school.SchoolDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolClassDto {

	private SchoolDto schoolDto;

	private List<ClassDto> classDto;

	
}
