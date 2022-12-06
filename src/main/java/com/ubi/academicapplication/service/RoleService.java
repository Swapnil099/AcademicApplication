package com.ubi.academicapplication.service;

import com.ubi.academicapplication.dto.roledto.RoleCreationDto;
import com.ubi.academicapplication.dto.roledto.RoleDto;
import com.ubi.academicapplication.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<Role> getRolesFromString(Set<String> roles);
    RoleDto createRole(RoleCreationDto roleCreationDTO);
    List<RoleDto> getAllRoles();

    Role getRoleByRoleType(String roleType);

    Boolean deleteRole(String roleType);
}
