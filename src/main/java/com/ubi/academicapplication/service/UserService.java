package com.ubi.academicapplication.service;
import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.dto.userdto.UserCreationDto;
import com.ubi.academicapplication.dto.userdto.UserDto;

import java.util.List;

public interface UserService {

    Response<List<UserDto>> getAllUsers();

    Response<UserDto> createNewUser(UserCreationDto userCreationDTO);

    Response<UserDto> getUserById(String userId);

    public UserDto getUserByUsername(String username);

    public Response<UserDto> deleteUserById(String userId);

    public boolean isUsernamePasswordValid(String username,String password);

    public String getRoleByUsername(String username);
}
