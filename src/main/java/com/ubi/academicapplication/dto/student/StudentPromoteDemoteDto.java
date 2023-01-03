package com.ubi.academicapplication.dto.student;

import lombok.*;

import java.util.Set;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentPromoteDemoteDto {
    private Long classId;
    private Set<Long> studentId ;
}