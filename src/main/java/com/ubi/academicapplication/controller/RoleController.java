package com.ubi.academicapplication.controller;

import com.ubi.academicapplication.dto.roledto.RoleCreationDTO;
import com.ubi.academicapplication.dto.roledto.RoleDTO;
import com.ubi.academicapplication.dto.roledto.RoleUserDTO;
import com.ubi.academicapplication.entity.Role;
import com.ubi.academicapplication.entity.User;
import com.ubi.academicapplication.mapper.RoleMapper;
import com.ubi.academicapplication.security.roleaccessinterface.IsPrincipal;
import com.ubi.academicapplication.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    RoleMapper roleMapper;

    @Operation(summary = "Create New Role", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<?> createNewRole(@RequestBody RoleCreationDTO roleCreationDTO) {
        if(roleService.getRoleByRoleType(roleCreationDTO.getRoleType()) != null){
            return ResponseEntity.ok().body("Role Already Exists");
        }
        RoleDTO roleDTO = roleService.createRole(roleCreationDTO);
        if(roleDTO == null) return ResponseEntity.internalServerError().body("Error Occured");
        return ResponseEntity.ok().body(roleDTO);
    }

    @Operation(summary = "Get all users with this role", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/users")
    public ResponseEntity<?> getUsersByRole(@RequestParam String roleType) {
        Role role = roleService.getRoleByRoleType(roleType);
        if(role == null){
            return ResponseEntity.ok().body("Given Role Not Exist");
        }
        Set<User> users = role.getUsers();
        Set<RoleUserDTO> roleUsers = roleMapper.toRoleUsers(users);
        return ResponseEntity.ok().body(roleUsers);
    }

    @Operation(summary = "Get All Roles Availaible", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    @IsPrincipal
    public ResponseEntity<?> getAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles();
        return ResponseEntity.ok().body(roles);
    }

    @Operation(summary = "Delete Role By Role Type", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{roleType}")
    public ResponseEntity<?> deleteRoleByRoleType(@PathVariable String roleType) {
        Boolean isDeleted = roleService.deleteRole(roleType);
        if(isDeleted!=null && !isDeleted) return ResponseEntity.ok().body("Role Does Not Exist");
        return ResponseEntity.ok().body("Role Deleted Successfully");
    }

}
