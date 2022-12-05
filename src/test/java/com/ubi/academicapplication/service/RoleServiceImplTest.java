package com.ubi.academicapplication.service;

import com.ubi.academicapplication.dto.roledto.RoleCreationDTO;
import com.ubi.academicapplication.dto.roledto.RoleDTO;
import com.ubi.academicapplication.entity.Role;
import com.ubi.academicapplication.mapper.RoleMapper;
import com.ubi.academicapplication.repository.RoleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    RoleRepository roleRepository;

    @Mock
    RoleMapper roleMapper;

    @InjectMocks
    RoleServiceImpl roleService;

    @Test
    @DisplayName("Should return true when the role is found")
    void deleteRoleWhenRoleIsFoundThenReturnTrue() {
        Role role = new Role("Admin", "ADMIN");
        when(roleRepository.getRoleByRoleType(any())).thenReturn(role);
        Boolean result = roleService.deleteRole("ADMIN");
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false when the role is not found")
    void deleteRoleWhenRoleIsNotFoundThenReturnFalse() {
        String roleType = "ADMIN";
        when(roleRepository.getRoleByRoleType(roleType)).thenReturn(null);

        Boolean result = roleService.deleteRole(roleType);

        assertFalse(result);
    }

    @Test
    @DisplayName("Should return all roles")
    void getAllRolesShouldReturnAllRoles() {
        Role role1 = new Role();
        List<Role> rolesList = new ArrayList<>();
        rolesList.add(role1);

        RoleDTO roleDTO1 = new RoleDTO();

        when(roleRepository.findAll()).thenReturn(rolesList);
        when(roleMapper.toDto(any())).thenReturn(roleDTO1);

        List<RoleDTO> result = roleService.getAllRoles();

        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Should return the role when the role is not existed")
    void createRoleWhenRoleIsNotExistedThenReturnTheRole() {
        Role role = new Role("roleName", "roleType");
        RoleCreationDTO roleCreationDTO = new RoleCreationDTO("roleName", "roleType");
        when(roleMapper.toRole(roleCreationDTO)).thenReturn(role);
        when(roleRepository.getRoleByRoleType(role.getRoleType())).thenReturn(null);
        when(roleRepository.save(role)).thenReturn(role);
        when(roleMapper.toDto(role)).thenReturn(new RoleDTO());

        RoleDTO roleDTO = roleService.createRole(roleCreationDTO);

        assertNotNull(roleDTO);
    }

    @Test
    @DisplayName("Should return the existed role when the role is already existed")
    void createRoleWhenRoleIsAlreadyExistedThenReturnTheExistedRole() {
        Role role = new Role("roleName", "roleType");
        RoleCreationDTO roleCreationDTO = new RoleCreationDTO("roleName", "roleType");
        when(roleRepository.getRoleByRoleType(role.getRoleType())).thenReturn(role);
        when(roleMapper.toRole(roleCreationDTO)).thenReturn(role);
        when(roleMapper.toDto(role)).thenReturn(new RoleDTO(1L, "roleName", "roleType"));

        RoleDTO roleDTO = roleService.createRole(roleCreationDTO);

        assertEquals("roleName", roleDTO.getRoleName());
        assertEquals("roleType", roleDTO.getRoleType());
    }

    @Test
    @DisplayName("Should return a set of roles when the input is a set of role types")
    void getRolesSetFromRoleTypeStringSet() {
        Set<String> roles = new HashSet<>();
        roles.add("ADMIN");
        roles.add("USER");

        Role role1 = new Role("Admin", "ADMIN");
        Role role2 = new Role("User", "USER");

        when(roleRepository.getRoleByRoleType("ADMIN")).thenReturn(role1);
        when(roleRepository.getRoleByRoleType("USER")).thenReturn(role2);

        Set<Role> result = roleService.getRolesFromString(roles);

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Should return a role when the input is string of role type")
    void getRoleFromRoleTypeString(){
        Role role = new Role("admin","ADMIN");
        when(roleRepository.getRoleByRoleType(any())).thenReturn(role);
        Role result = roleService.getRoleByRoleType(any());
        assertEquals(role, result);
    }
}