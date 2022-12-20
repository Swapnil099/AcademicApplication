package com.ubi.academicapplication.dto.classdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data 
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClassDetailDto {
	private int classId;
	private String classCode;
	private String className;
//	public void setClassDetailsDto(ClassDetailDto entityToDto) {
//		// TODO Auto-generated method stub
//		
//	}
}
