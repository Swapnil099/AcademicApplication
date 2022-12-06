package com.ubi.academicapplication.service;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.dto.roledto.RoleDto;
import com.ubi.academicapplication.dto.userdto.UserCreationDto;
import com.ubi.academicapplication.dto.userdto.UserDto;
import com.ubi.academicapplication.entity.Role;
import com.ubi.academicapplication.entity.User;
import com.ubi.academicapplication.error.CustomException;
import com.ubi.academicapplication.error.HttpStatusCode;
import com.ubi.academicapplication.error.Result;
import com.ubi.academicapplication.mapper.UserMapper;
import com.ubi.academicapplication.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    Result result;

    @Override
    public Response<List<UserDto>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> allUsers = users.stream().map(userMapper::toDto).collect(Collectors.toList());
        Response<List<UserDto>> response = new Response<>();
        response.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
        response.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
        response.setResult(new Result<List<UserDto>>(allUsers));
        return response;
    }

    @Override
    public Response<UserDto> createNewUser(UserCreationDto userCreationDTO) {
        Response<UserDto> response = new Response<>();
        if(this.getUserByUsername(userCreationDTO.getUsername()) != null){
            throw new CustomException(
                    HttpStatusCode.RESOURCE_ALREADY_EXISTS.getCode(),
                    HttpStatusCode.RESOURCE_ALREADY_EXISTS,
                    HttpStatusCode.RESOURCE_ALREADY_EXISTS.getMessage(),
                    result);
        }

        User user = userMapper.toUser(userCreationDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User currUser = userRepository.save(user);
        if(currUser == null) {
            throw new CustomException(
                    HttpStatusCode.INTERNAL_SERVER_ERROR.getCode(),
                    HttpStatusCode.INTERNAL_SERVER_ERROR,
                    HttpStatusCode.INTERNAL_SERVER_ERROR.getMessage(),
                    result);
        }
        UserDto userDto = userMapper.toDto(currUser);
        response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
        response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
        response.setResult(new Result<UserDto>(userDto));
        return response;
    }

    @Override
    public Response<UserDto> getUserById(String userId) {
        Optional<User> currUser = userRepository.findById(Long.parseLong(userId));
        Response<UserDto> response = new Response<>();
        if(!currUser.isPresent()) {
            throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(),
                    HttpStatusCode.RESOURCE_NOT_FOUND,
                    HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(),
                    result);
        }
        User user = currUser.get();
        UserDto userDto = userMapper.toDto(user);
        response.setStatusCode(HttpStatusCode.RESOURCE_NOT_FOUND.getCode());
        response.setMessage(HttpStatusCode.RESOURCE_NOT_FOUND.getMessage());
        response.setResult(new Result<UserDto>(userDto));
        return response;
    }

    public UserDto getUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user == null) return null;
        return userMapper.toDto(user);
    }

    @Override
    public Response<UserDto> deleteUserById(String userId) {
        Optional<User> currUser = userRepository.findById(Long.parseLong(userId));
        if(!currUser.isPresent()) {
            throw new CustomException(HttpStatusCode.RESOURCE_NOT_FOUND.getCode(),
                    HttpStatusCode.RESOURCE_NOT_FOUND,
                    HttpStatusCode.RESOURCE_NOT_FOUND.getMessage(),
                    result);
        }
        userRepository.deleteById(Long.parseLong(userId));
        Response<UserDto> response = new Response<>();
        response.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
        response.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
        response.setResult(new Result<UserDto>(userMapper.toDto(currUser.get())));
        return response;
    }

    @Override
    public boolean isUsernamePasswordValid(String username,String password){
        User user = userRepository.findByUsername(username);
        return (user != null && passwordEncoder.matches(password,user.getPassword()));
    }

    @Override
    public String getRoleByUsername(String username) {
        UserDto user = this.getUserByUsername(username);
        if(user.getRoles().size() > 0) {
            for (String role : user.getRoles()) {
                return role;
            }
        }
        return "";
    }
}
