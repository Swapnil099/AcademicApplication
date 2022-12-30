package com.ubi.academicapplication.service;
import java.io.ByteArrayInputStream;
import java.util.List;

import com.ubi.academicapplication.dto.response.Response;
import com.ubi.academicapplication.dto.user.UserContactInfoDto;
import com.ubi.academicapplication.dto.user.UserContactInfoMappingDto;
import com.ubi.academicapplication.dto.user.UserCreationDto;
import com.ubi.academicapplication.dto.user.UserDto;
import com.ubi.academicapplication.entity.User;

public interface UserService {

	Response<List<UserContactInfoDto>> getAllUsers();

	Response<User> createNewUser(UserCreationDto userCreationDTO);

	Response<UserDto> getUserById(String userId);

	UserDto getUserByUsername(String username);

	Response<UserDto> deleteUserById(String userId);

	boolean isUsernamePasswordValid(String username,String password);

	String getRoleByUsername(String username);

	Response<UserDto> changeActiveStatusToTrue(String userId);

	Response<UserDto> changeActiveStatusToFalse(String userId);

	Response<String> changeSelfPassword(String userId, String newPassword);

	Response<UserCreationDto> updateUserById(String userId,UserCreationDto userCreationDto);

	Response<UserContactInfoDto> addContactInfo(UserContactInfoMappingDto userContactInfoMappingDto);

	Response<UserContactInfoDto> getContactInfowithUser(Long id);

	ByteArrayInputStream load();
}
