package com.ubi.academicapplication.service;

import com.ubi.academicapplication.dto.userdto.UserCreationDTO;
import com.ubi.academicapplication.dto.userdto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO createNewUser(UserCreationDTO userCreationDTO);

    UserDTO getUserById(String userId);

    public UserDTO getUserByUsername(String username);

    public Boolean deleteUserById(String userId);

    public boolean isUsernamePasswordValid(String username,String password);
}
