package com.ubi.academicapplication.service;

import java.util.List;
import java.util.Set;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.dto.roledto.RoleCreationDto;
import com.ubi.academicapplication.dto.roledto.RoleDto;
import com.ubi.academicapplication.dto.roledto.RoleUserDto;
import com.ubi.academicapplication.entity.Role;

public interface RoleService {
    Role getRoleFromString(String roleTye);
    Response<RoleDto> createRole(RoleCreationDto roleCreationDTO);
    Response<List<RoleDto>> getAllRoles();

    Role getRoleByRoleType(String roleType);

    Response<RoleDto> deleteRole(String roleType);

    Response<Set<RoleUserDto>> getUsersByRoleName(String roleType);
}
