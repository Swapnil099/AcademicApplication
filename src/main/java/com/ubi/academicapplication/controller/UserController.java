package com.ubi.academicapplication.controller;

import java.util.List;

import com.ubi.academicapplication.dto.user.PasswordChangeDto;
import com.ubi.academicapplication.dto.user.UserContactInfoDto;
import com.ubi.academicapplication.dto.user.UserContactInfoMappingDto;
import com.ubi.academicapplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.user.UserCreationDto;
import com.ubi.academicapplication.dto.user.UserDto;
import com.ubi.academicapplication.security.roleaccessinterface.IsPrincipal;
import com.ubi.academicapplication.security.roleaccessinterface.IsSuperAdmin;
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
    @IsSuperAdmin
    public ResponseEntity<Response<User>> createUser(@RequestBody UserCreationDto userCreationDTO){
        Response<User> userResponse = userService.createNewUser(userCreationDTO);
        return ResponseEntity.ok().body(userResponse);
    }


    @Operation(summary = "Get All Users", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    @IsPrincipal
    public ResponseEntity<Response<List<UserContactInfoDto>>> getAllUsers() {
        Response<List<UserContactInfoDto>> allUserDtoResponse = userService.getAllUsers();
        return ResponseEntity.ok().body(allUserDtoResponse);
    }

    @Operation(summary = "Get User By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{userId}")
    @IsSuperAdmin
    public ResponseEntity<Response<UserDto>> getUserById(@PathVariable String userId) {
        Response<UserDto> response = userService.getUserById(userId);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Delete User By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping("/{userId}")
    @IsSuperAdmin
    public ResponseEntity<Response<UserDto>> deleteUserById(@PathVariable String userId) {
        Response<UserDto> response = userService.deleteUserById(userId);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Change Active Status To True Of User By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/activate/{userId}")
    @IsSuperAdmin
    public ResponseEntity<Response<UserDto>> activateUserById(@PathVariable String userId) {
        Response<UserDto> response = userService.changeActiveStatusToTrue(userId);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Change Active Status To False Of User By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping("/deactivate/{userId}")
    @IsSuperAdmin
    public ResponseEntity<Response<UserDto>> deactivateUserById(@PathVariable String userId) {
        Response<UserDto> response = userService.changeActiveStatusToFalse(userId);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Change Password Of User By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping ("/password/{userId}")
    public ResponseEntity<Response<String>> changePassword(@PathVariable String userId, @RequestBody PasswordChangeDto passwordChangeDto) {
        Response<String> response = userService.changeSelfPassword(userId, passwordChangeDto.getNewPassword());
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Update User By Id", security = @SecurityRequirement(name = "bearerAuth"))
    @PatchMapping ("/{userId}")
    @IsSuperAdmin
    public ResponseEntity<Response<UserCreationDto>> updateUserById(@PathVariable String userId, @RequestBody UserCreationDto userCreationDto) {
        Response<UserCreationDto> response = userService.updateUserById(userId, userCreationDto);
        return ResponseEntity.ok().body(response);
    }
    
    @Operation(summary = "Map User and  ContactInfo", security = @SecurityRequirement(name = "bearerAuth"))
	@PostMapping("/addContactInfo")
    public ResponseEntity<Response<UserContactInfoDto>> addContactInfo(@RequestBody UserContactInfoMappingDto userContactDto) {
    	System.out.println(userContactDto.toString());
		Response<UserContactInfoDto> response=userService.addContactInfo(userContactDto);
		 return ResponseEntity.ok().body(response);
	}
    
    @Operation(summary = "Get ContactInfo In User", security = @SecurityRequirement(name = "bearerAuth"))
	@GetMapping("/getUser/{id}")
    public ResponseEntity<Response<UserContactInfoDto>> getContactInfowithUser(@PathVariable Long id) {
		Response<UserContactInfoDto> response = userService.getContactInfowithUser(id);
		return ResponseEntity.ok().body(response);
	}
    
    @Operation(summary="Download file ",security=@SecurityRequirement(name= "bearerAuth"))
   	@GetMapping("/download")
   	public ResponseEntity<Resource> getCSVFileData()
   	{
   	    String filename = "user.csv";
   	    InputStreamResource file = new InputStreamResource(userService.load());

   	    return ResponseEntity.ok()
   	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
   	        .contentType(MediaType.parseMediaType("application/csv"))
   	        .body(file);
   	}
}
