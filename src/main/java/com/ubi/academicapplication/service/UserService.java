package com.ubi.academicapplication.service;
import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.user.UserCreationDto;
import com.ubi.academicapplication.dto.user.UserDto;
import com.ubi.academicapplication.entity.UserInfo;

import java.util.List;

public interface UserService {

    Response<List<UserDto>> getAllUsers();

    Response<UserInfo> createNewUser(UserCreationDto userCreationDTO);

    Response<UserDto> getUserById(String userId);

     UserDto getUserByUsername(String username);

     Response<UserDto> deleteUserById(String userId);

     boolean isUsernamePasswordValid(String username,String password);

     String getRoleByUsername(String username);

     Response<UserDto> changeActiveStatusToTrue(String userId);

     Response<UserDto> changeActiveStatusToFalse(String userId);

     Response<String> changeSelfPassword(String userId, String newPassword);

     Response<UserDto> updateUserById(String userId,UserCreationDto userCreationDto);
}
