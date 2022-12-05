package com.ubi.academicapplication.service;

import com.ubi.academicapplication.dto.roledto.RoleCreationDTO;
import com.ubi.academicapplication.dto.roledto.RoleDTO;
import com.ubi.academicapplication.entity.Role;
import com.ubi.academicapplication.mapper.RoleMapper;
import com.ubi.academicapplication.repository.RoleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Data
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Set<Role> getRolesFromString(Set<String> roles) {
        return roles.stream()
                .map(roleRepository::getRoleByRoleType)
                .filter(role -> role!=null)
                .collect(Collectors.toSet());
    }

    @Override
    public RoleDTO createRole(RoleCreationDTO roleCreationDTO) {
        Role role = roleMapper.toRole(roleCreationDTO);
        Role currRole = roleRepository.getRoleByRoleType(role.getRoleType());
        if(currRole == null) currRole = roleRepository.save(role);
        return roleMapper.toDto(currRole);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Role getRoleByRoleType(String roleType) {
        return roleRepository.getRoleByRoleType(roleType);
    }

    @Override
    public Boolean deleteRole(String roleType) {
        Role role = roleRepository.getRoleByRoleType(roleType);
        if(role == null) return false;
        roleRepository.delete(role);
        return true;
    }
}
