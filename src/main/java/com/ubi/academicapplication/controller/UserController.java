package com.ubi.academicapplication.controller;

import java.util.List;

import com.ubi.academicapplication.dto.user.PasswordChangeDto;
import com.ubi.academicapplication.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.user.UserCreationDto;
import com.ubi.academicapplication.dto.user.UserDto;
import com.ubi.academicapplication.security.roleaccessinterface.*;
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
    public ResponseEntity<Response<UserInfo>> createUser(@RequestBody UserCreationDto userCreationDTO){
        Response<UserInfo> userResponse = userService.createNewUser(userCreationDTO);
        return ResponseEntity.ok().body(userResponse);
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

    @Operation(summary = "Change Active Status To True Of User By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/activate/{userId}")
    public ResponseEntity<Response<UserDto>> activateUserById(@PathVariable String userId) {
        Response<UserDto> response = userService.changeActiveStatusToTrue(userId);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Change Active Status To False Of User By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/deactivate/{userId}")
    public ResponseEntity<Response<UserDto>> deactivateUserById(@PathVariable String userId) {
        Response<UserDto> response = userService.changeActiveStatusToFalse(userId);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Change Password Of User By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping ("/password/{userId}")
    public ResponseEntity<Response<String>> deactivateUserById(@PathVariable String userId, @RequestBody PasswordChangeDto passwordChangeDto) {
        Response<String> response = userService.changeSelfPassword(userId, passwordChangeDto.getNewPassword());
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Update User By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping ("/{userId}")
    @IsSuperAdmin
    public ResponseEntity<Response<UserDto>> updateUserById(@PathVariable String userId, @RequestBody UserCreationDto userCreationDto) {
        Response<UserDto> response = userService.updateUserById(userId, userCreationDto);
        return ResponseEntity.ok().body(response);
    }
}
