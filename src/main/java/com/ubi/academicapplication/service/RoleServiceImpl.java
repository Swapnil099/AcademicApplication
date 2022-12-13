package com.ubi.academicapplication.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.role.RoleCreationDto;
import com.ubi.academicapplication.dto.role.RoleDto;
import com.ubi.academicapplication.dto.role.RoleUserDto;
import com.ubi.academicapplication.entity.Role;
import com.ubi.academicapplication.entity.UserInfo;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.mapper.RoleMapper;
import com.ubi.academicapplication.repository.RoleRepository;

import lombok.Data;

@Service
@Data
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    Result result;

    @Override
    public Role getRoleFromString(String roleType) {
        Role role = roleRepository.getRoleByRoleType(roleType);
        return role;
    }

    @Override
    public Response<RoleDto> createRole(RoleCreationDto roleCreationDTO) {
        Role role = roleMapper.toRole(roleCreationDTO);
        Role currRole = roleRepository.getRoleByRoleType(roleCreationDTO.getRoleType());
        Response<RoleDto> response = new Response<>();
        if(currRole != null) {
            throw new CustomException(
                    HttpStatusCode.RESOURCE_ALREADY_EXISTS.getCode(),
                    HttpStatusCode.RESOURCE_ALREADY_EXISTS,
                    HttpStatusCode.RESOURCE_ALREADY_EXISTS.getMessage(),
                    result);
        }
        Role savedRole = roleRepository.save(role);
        if(savedRole == null) {
            throw new CustomException(
                    HttpStatusCode.INTERNAL_SERVER_ERROR.getCode(),
                    HttpStatusCode.INTERNAL_SERVER_ERROR,
                    HttpStatusCode.INTERNAL_SERVER_ERROR.getMessage(),
                    result);
        }
        response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
        response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
        response.setResult(new Result<RoleDto>(roleMapper.toDto(savedRole)));
        return response;
    }

    @Override
    public Response<List<RoleDto>> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> rolesDtoList = roles.stream().map(roleMapper::toDto).collect(Collectors.toList());
        Response<List<RoleDto>> response = new Response<>();
        response.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
        response.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
        response.setResult(new Result<List<RoleDto>>(rolesDtoList));
        return response;
    }

    @Override
    public Role getRoleByRoleType(String roleType) {
        return roleRepository.getRoleByRoleType(roleType);
    }

    @Override
    public Response<RoleDto> deleteRole(String roleType) {
        Role role = roleRepository.getRoleByRoleType(roleType);
        Response<RoleDto> response = new Response<>();
        if(role == null) {
            throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(),
                    HttpStatusCode.RESOURCE_NOT_FOUND,
                    HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(),
                    result);
        }

        roleRepository.delete(role);
        response.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
        response.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
        response.setResult(new Result<RoleDto>(roleMapper.toDto(role)));
        return response;
    }

    @Override
    public Response<Set<RoleUserDto>> getUsersByRoleName(String roleType) {
        Role role = this.getRoleByRoleType(roleType);
        if(role == null){
            throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(),
                    HttpStatusCode.RESOURCE_NOT_FOUND,
                    HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(),
                    result);
        }
        Set<UserInfo> users = role.getUsers();
        Set<RoleUserDto> roleUsers = roleMapper.toRoleUsers(users);
        Response<Set<RoleUserDto>> response = new Response<>();
        response.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
        response.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
        response.setResult(new Result<Set<RoleUserDto>>(roleUsers));
        return response;
    }

    @Override
    public Response<RoleDto> updateRoleById(String roleId,RoleCreationDto roleCreationDto) {
        Role roleWithGivenRoleType = roleRepository.getRoleByRoleType(roleCreationDto.getRoleType());
        if(roleWithGivenRoleType != null) {
            throw new CustomException(
                    HttpStatusCode.ROLETYPE_NOT_AVAILAIBLE.getCode(),
                    HttpStatusCode.ROLETYPE_NOT_AVAILAIBLE,
                    HttpStatusCode.ROLETYPE_NOT_AVAILAIBLE.getMessage(),
                    result);
        }
        Role role = roleRepository.getReferenceById(Long.parseLong(roleId));
        if(role == null){
            throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(),
                    HttpStatusCode.RESOURCE_NOT_FOUND,
                    HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(),
                    result);
        }

        role.setRoleName(roleCreationDto.getRoleName());
        role.setRoleType(roleCreationDto.getRoleType());
        roleRepository.save(role);

        Response response = new Response<>();
        response.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
        response.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
        response.setResult(new Result<>(roleMapper.toDto(role)));
        return response;
    }
}
