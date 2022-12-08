package com.ubi.academicapplication.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.dto.roledto.RoleCreationDto;
import com.ubi.academicapplication.dto.roledto.RoleDto;
import com.ubi.academicapplication.dto.roledto.RoleUserDto;
import com.ubi.academicapplication.mapper.RoleMapper;
import com.ubi.academicapplication.security.roleaccessinterface.IsPrincipal;
import com.ubi.academicapplication.service.RoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

//    @Autowired
//    RoleMapper roleMapper;

    @Operation(summary = "Create New Role", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<Response<RoleDto>> createNewRole(@RequestBody RoleCreationDto roleCreationDTO) {
        Response<RoleDto> roleDTO = roleService.createRole(roleCreationDTO);
        return ResponseEntity.ok().body(roleDTO);
    }

    @Operation(summary = "Get all users with this role", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/users")
    public ResponseEntity<Response<Set<RoleUserDto>>> getUsersByRole(@RequestParam String roleType) {
        Response<Set<RoleUserDto>> roleUsers = roleService.getUsersByRoleName(roleType);
        return ResponseEntity.ok().body(roleUsers);
    }

    @Operation(summary = "Get All Roles Availaible", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    @IsPrincipal
    public ResponseEntity<Response<List<RoleDto>>> getAllRoles() {
        Response<List<RoleDto>> roles = roleService.getAllRoles();
        return ResponseEntity.ok().body(roles);
    }

    @Operation(summary = "Delete Role By Role Type", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{roleType}")
    public ResponseEntity<Response<RoleDto>> deleteRoleByRoleType(@PathVariable String roleType) {
        Response<RoleDto> roleDto = roleService.deleteRole(roleType);
        return ResponseEntity.ok().body(roleDto);
    }

}
