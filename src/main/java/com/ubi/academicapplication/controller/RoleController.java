package com.ubi.academicapplication.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.role.RoleCreationDto;
import com.ubi.academicapplication.dto.role.RoleDto;
import com.ubi.academicapplication.dto.role.RoleUserDto;
import com.ubi.academicapplication.security.roleaccessinterface.IsPrincipal;
import com.ubi.academicapplication.security.roleaccessinterface.IsSuperAdmin;
import com.ubi.academicapplication.service.RoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;


    @Operation(summary = "Create New Role", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    @IsSuperAdmin
    public ResponseEntity<Response<RoleDto>> createNewRole(@RequestBody RoleCreationDto roleCreationDTO) {
        Response<RoleDto> roleDTO = roleService.createRole(roleCreationDTO);
        return ResponseEntity.ok().body(roleDTO);
    }

    @Operation(summary = "Get all users with this role", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/users")
    @IsSuperAdmin
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
    @IsSuperAdmin
    public ResponseEntity<Response<RoleDto>> deleteRoleByRoleType(@PathVariable String roleType) {
        Response<RoleDto> roleDto = roleService.deleteRole(roleType);
        return ResponseEntity.ok().body(roleDto);
    }

    @Operation(summary = "Update Role By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/{roleId}")
    @IsSuperAdmin
    public ResponseEntity<Response<RoleDto>> updateUserById(@PathVariable String roleId, @RequestBody RoleCreationDto roleCreationDto) {
        Response<RoleDto> response = roleService.updateRoleById(roleId, roleCreationDto);
        return ResponseEntity.ok().body(response);
    }

}
