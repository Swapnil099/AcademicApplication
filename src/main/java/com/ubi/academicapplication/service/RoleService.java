package com.ubi.academicapplication.service;

import com.ubi.academicapplication.dto.roledto.RoleCreationDTO;
import com.ubi.academicapplication.dto.roledto.RoleDTO;
import com.ubi.academicapplication.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<Role> getRolesFromString(Set<String> roles);
    RoleDTO createRole(RoleCreationDTO roleCreationDTO);
    List<RoleDTO> getAllRoles();

    Role getRoleByRoleType(String roleType);

    Boolean deleteRole(String roleType);
}
