package com.ubi.academicapplication.dto.roledto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    Long id;
    String roleName;
    String roleType;
}
