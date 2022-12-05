package com.ubi.academicapplication.mapper;

import com.ubi.academicapplication.dto.roledto.RoleCreationDTO;
import com.ubi.academicapplication.dto.roledto.RoleDTO;
import com.ubi.academicapplication.dto.roledto.RoleUserDTO;
import com.ubi.academicapplication.entity.Role;
import com.ubi.academicapplication.entity.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper {
    public RoleDTO toDto(Role role){
        return new RoleDTO(role.getId(),role.getRoleName(),role.getRoleType());
    }

    public Role toRole(RoleDTO roleDTO){
        return new Role(roleDTO.getRoleName(),roleDTO.getRoleType());
    }

    public Role toRole(RoleCreationDTO roleCreationDTO){
        return new Role(roleCreationDTO.getRoleName(),roleCreationDTO.getRoleType());
    }

    public RoleUserDTO toRoleUserDTO(User user){
        return new RoleUserDTO(user.getId(),user.getFirstName(),user.getLastName());

    }

    public Set<RoleUserDTO> toRoleUsers(Set<User> users){
        return users.stream().map(this::toRoleUserDTO).collect(Collectors.toSet());
    }

}
