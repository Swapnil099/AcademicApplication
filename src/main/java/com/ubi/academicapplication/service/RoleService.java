package com.ubi.academicapplication.service;

import java.util.List;
import java.util.Set;

import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.role.RoleCreationDto;
import com.ubi.academicapplication.dto.role.RoleDto;
import com.ubi.academicapplication.dto.role.RoleUserDto;
import com.ubi.academicapplication.entity.Role;

public interface RoleService {
    Role getRoleFromString(String roleTye);
    Response<RoleDto> createRole(RoleCreationDto roleCreationDTO);
    Response<List<RoleDto>> getAllRoles();

    Role getRoleByRoleType(String roleType);

    Response<RoleDto> deleteRole(String roleType);

    Response<Set<RoleUserDto>> getUsersByRoleName(String roleType);

    Response<RoleDto> updateRoleById(String roleId,RoleCreationDto roleCreationDto);
}
