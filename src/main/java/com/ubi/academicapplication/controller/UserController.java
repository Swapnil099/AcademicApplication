package com.ubi.academicapplication.controller;

import com.ubi.academicapplication.dto.userdto.UserCreationDTO;
import com.ubi.academicapplication.dto.userdto.UserDTO;
import com.ubi.academicapplication.security.roleaccessinterface.IsSuperAdmin;
import com.ubi.academicapplication.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Create New User", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreationDTO userCreationDTO){
        if(userService.getUserByUsername(userCreationDTO.getUsername()) != null){
            return ResponseEntity.ok().body("user already Exist");
        }
        UserDTO userDTO = userService.createNewUser(userCreationDTO);
        if(userDTO == null) return ResponseEntity.internalServerError().body("Error Occured During User Creation");
        return ResponseEntity.ok().body(userDTO);
    }

    @Operation(summary = "Get All Users", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    @IsSuperAdmin
    public ResponseEntity<?> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }

    @Operation(summary = "Get User By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllUserById(@PathVariable String userId) {
        UserDTO userDTO = userService.getUserById(userId);
        if(userDTO == null) return ResponseEntity.badRequest().body("User Not Found");
        return ResponseEntity.ok().body(userDTO);
    }

    @Operation(summary = "Delete User By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable String userId) {
        Boolean isDeleted = userService.deleteUserById(userId);
        if(isDeleted!=null && !isDeleted) return ResponseEntity.ok().body("User Not Found");
        return ResponseEntity.ok().body("User Deleted Successfully");
    }

}
