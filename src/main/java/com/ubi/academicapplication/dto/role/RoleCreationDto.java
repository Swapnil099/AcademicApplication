package com.ubi.academicapplication.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
@AllArgsConstructor
public class RoleCreationDto {
    String roleName;
    String roleType;
}
