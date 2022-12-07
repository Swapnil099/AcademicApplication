package com.ubi.academicapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.dto.userdto.UserCreationDto;
import com.ubi.academicapplication.dto.userdto.UserDto;
import com.ubi.academicapplication.security.roleaccessinterface.IsPrincipal;
import com.ubi.academicapplication.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Create New User", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    public ResponseEntity<Response<UserDto>> createUser(@RequestBody UserCreationDto userCreationDTO){
        Response<UserDto> userDtoResponse = userService.createNewUser(userCreationDTO);
        return ResponseEntity.ok().body(userDtoResponse);
    }

    @Operation(summary = "Get All Users", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    @IsPrincipal
    public ResponseEntity<Response<List<UserDto>>> getAllUsers() {
        Response<List<UserDto>> allUserDtoResponse = userService.getAllUsers();
        return ResponseEntity.ok().body(allUserDtoResponse);
    }

    @Operation(summary = "Get User By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{userId}")
    public ResponseEntity<Response<UserDto>> getUserById(@PathVariable String userId) {
        Response<UserDto> response = userService.getUserById(userId);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Delete User By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{userId}")
    public ResponseEntity<Response<UserDto>> deleteUserById(@PathVariable String userId) {
        Response<UserDto> response = userService.deleteUserById(userId);
        return ResponseEntity.ok().body(response);
    }

}
