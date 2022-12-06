package com.ubi.academicapplication.service;

import com.ubi.academicapplication.dto.responsedto.Response;
import com.ubi.academicapplication.dto.roledto.RoleDto;
import com.ubi.academicapplication.dto.userdto.UserCreationDto;
import com.ubi.academicapplication.dto.userdto.UserDto;
import com.ubi.academicapplication.entity.Role;
import com.ubi.academicapplication.entity.User;
import com.ubi.academicapplication.error.HttpStatusCode;
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

    @Override
    public Response<List<UserDto>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> allUsers = users.stream().map(userMapper::toDto).collect(Collectors.toList());
        Response<List<UserDto>> response = new Response<>();
        response.setStatusCode(HttpStatusCode.SUCCESSFUL.getCode());
        response.setMessage(HttpStatusCode.SUCCESSFUL.getMessage());
        response.setResult(allUsers);
        return response;
    }

    @Override
    public Response<UserDto> createNewUser(UserCreationDto userCreationDTO) {
        Response<UserDto> response = new Response<>();
        if(this.getUserByUsername(userCreationDTO.getUsername()) != null){
            response.setStatusCode(HttpStatusCode.RESOURCE_ALREADY_EXISTS.getCode());
            response.setMessage(HttpStatusCode.RESOURCE_ALREADY_EXISTS.getMessage());
            return response;
        }

        User user = userMapper.toUser(userCreationDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User currUser = userRepository.save(user);
        if(currUser == null) {
            response.setStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR.getCode());
            response.setMessage(HttpStatusCode.INTERNAL_SERVER_ERROR.getMessage());
            return response;
        }
        UserDto userDto = userMapper.toDto(currUser);
        response.setStatusCode(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getCode());
        response.setMessage(HttpStatusCode.RESOURCE_CREATED_SUCCESSFULLY.getMessage());
        response.setResult(userDto);
        return response;
    }

    @Override
    public UserDto getUserById(String userId) {
        Optional<User> currUser = userRepository.findById(Long.parseLong(userId));
        if(!currUser.isPresent()) return null;
        User user = currUser.get();
        return userMapper.toDto(user);
    }

    public UserDto getUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user == null) return null;
        return userMapper.toDto(user);
    }

    @Override
    public Boolean deleteUserById(String userId) {
        Optional<User> currUser = userRepository.findById(Long.parseLong(userId));
        if(!currUser.isPresent()) return false;
        userRepository.deleteById(Long.parseLong(userId));
        return true;
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
