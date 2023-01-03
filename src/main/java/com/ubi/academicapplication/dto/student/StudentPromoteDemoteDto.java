package com.ubi.academicapplication.dto.student;

import java.util.List;
import java.util.Set;

import com.ubi.academicapplication.entity.Student;
import com.ubi.academicapplication.entity.StudentPromoteDemote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class StudentPromoteDemoteDto {
	
	

	//private Long userId;
	private Long classId;
  private Set<Long> studentId ;
	

	
}
