package com.ubi.academicapplication.service;

import com.ubi.academicapplication.dto.userdto.UserCreationDTO;
import com.ubi.academicapplication.dto.userdto.UserDTO;
import com.ubi.academicapplication.entity.User;
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
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO createNewUser(UserCreationDTO userCreationDTO) {
        User user = userMapper.toUser(userCreationDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User currUser = userRepository.findByUsername(userCreationDTO.getUsername());
        if(currUser == null) {
            currUser = userRepository.save(user);
        }
        return userMapper.toDto(currUser);
    }

    @Override
    public UserDTO getUserById(String userId) {
        Optional<User> currUser = userRepository.findById(Long.parseLong(userId));
        if(!currUser.isPresent()) return null;
        User user = currUser.get();
        return userMapper.toDto(user);
    }

    public UserDTO getUserByUsername(String username){
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
        return (user != null && passwordEncoder.matches(password,user.getPassword()))?true:false;
    }
}
