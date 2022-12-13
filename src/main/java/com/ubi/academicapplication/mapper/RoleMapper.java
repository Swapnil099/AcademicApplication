package com.ubi.academicapplication.mapper;

import com.ubi.academicapplication.dto.role.RoleCreationDto;
import com.ubi.academicapplication.dto.role.RoleDto;
import com.ubi.academicapplication.dto.role.RoleUserDto;
import com.ubi.academicapplication.entity.Role;
import com.ubi.academicapplication.entity.UserInfo;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper {
    public RoleDto toDto(Role role){
        return new RoleDto(role.getId(),role.getRoleName(),role.getRoleType());
    }

    public Role toRole(RoleDto roleDTO){
        return new Role(roleDTO.getRoleName(),roleDTO.getRoleType());
    }

    public Role toRole(RoleCreationDto roleCreationDTO){
        return new Role(roleCreationDTO.getRoleName(),roleCreationDTO.getRoleType());
    }

    public RoleUserDto toRoleUserDTO(UserInfo user){
        return new RoleUserDto(user.getId(),user.getUsername());

    }

    public Set<RoleUserDto> toRoleUsers(Set<UserInfo> users){
        return users.stream().map(this::toRoleUserDTO).collect(Collectors.toSet());
    }

}
