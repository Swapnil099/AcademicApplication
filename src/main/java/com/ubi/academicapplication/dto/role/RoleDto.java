package com.ubi.academicapplication.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
@AllArgsConstructor
public class RoleDto {
    Long id;
    String roleName;
    String roleType;
}
