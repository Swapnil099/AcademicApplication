package com.ubi.academicapplication.dto.roledto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
@AllArgsConstructor
public class RoleCreationDto {
    String roleName;
    String roleType;
}
