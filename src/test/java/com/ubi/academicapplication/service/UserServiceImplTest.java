//package com.ubi.academicapplication.service;
//
//import com.ubi.academicapplication.dto.userdto.UserCreationDto;
//import com.ubi.academicapplication.dto.userdto.UserDto;
//import com.ubi.academicapplication.entity.User;
//import com.ubi.academicapplication.mapper.UserMapper;
//import com.ubi.academicapplication.repository.UserRepository;
//import org.junit.Assert;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//
//@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
//class UserServiceImplTest {
//
//    @MockBean
//    UserRepository userRepository;
//
//    @MockBean
//    UserMapper userMapper;
//
//    @MockBean
//    PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    UserServiceImpl userServiceImpl;
//
//    @Test
//    @DisplayName("Should return true when the user is found & Deleted")
//    void deleteUserByIdWhenUserIsFoundThenReturnTrue() {
//        User user = new User("firstName", "lastName", "username", "password", new HashSet<>());
//        user.setId(1L);
//        Mockito.when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
//        Mockito.when(userRepository.existsById(1L)).thenReturn(true);
//        Assert.assertTrue(userServiceImpl.deleteUserById("1"));
//    }
//
//    @Test
//    @DisplayName("Should return false when the user is not found")
//    void deleteUserByIdWhenUserIsNotFoundThenReturnFalse() {
//        String userId = "1";
//        Mockito.when(userRepository.findById(Long.parseLong(userId))).thenReturn(Optional.empty());
//        Boolean result = userServiceImpl.deleteUserById(userId);
//        Assert.assertFalse(result);
//    }
//
//    @Test
//    @DisplayName("Should return null when the user is not found")
//    void getUserByUsernameWhenUserIsNotFoundThenReturnNull() {
//        String username = "test";
//        Mockito.when(userRepository.findByUsername(username)).thenReturn(null);
//        UserDto userDTO = userServiceImpl.getUserByUsername(username);
//        Assert.assertNull(userDTO);
//    }
//
//    @Test
//    @DisplayName("Should return the user when the user is found")
//    void getUserByUsernameWhenUserIsFoundThenReturnTheUser() {
//        User user = new User("firstName", "lastName", "username", "password", new HashSet<>());
//        UserCreationDto userCreationDTO =
//                new UserCreationDto(
//                        "firstName", "lastName", "username", "password", new HashSet<>());
//        UserDto userDTO = new UserDto(1L, "firstName lastName", new HashSet<>());
//
//        Mockito.when(userRepository.findByUsername("username")).thenReturn(user);
//        Mockito.when(userMapper.toUser(userCreationDTO)).thenReturn(user);
//        Mockito.when(userMapper.toDto(user)).thenReturn(userDTO);
//
//        UserDto result = userServiceImpl.getUserByUsername("username");
//
//        Assert.assertEquals(result, userDTO);
//    }
//
//    @Test
//    @DisplayName("Should return null when the userid is not found")
//    void getUserByIdWhenUserIdIsNotFoundThenReturnNull() {
//        String userId = "1";
//        Mockito.when(userRepository.findById(Long.parseLong(userId))).thenReturn(Optional.empty());
//        UserDto userDTO = userServiceImpl.getUserById(userId);
//        Assert.assertNull(userDTO);
//    }
//
//    @Test
//    @DisplayName("Should return the user when the userid is found")
//    void getUserByIdWhenUserIdIsFoundThenReturnTheUser() {
//        User user = new User(1L, "firstName", "lastName", "username", "password", new HashSet<>());
//
//        UserDto userDTO = new UserDto(1L, "firstName lastName", new HashSet<>());
//        Mockito.when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
//        Mockito.when(userMapper.toDto(user)).thenReturn(userDTO);
//        UserDto result = userServiceImpl.getUserById("1");
//        Assert.assertEquals(userDTO, result);
//    }
//
//    @Test
//    @DisplayName("Should return all users")
//    void getAllUsersShouldReturnAllUsers() {
//        User user = new User(1L, "firstName", "lastName", "username", "password", new HashSet<>());
//        List<User> users = new ArrayList<>();
//        users.add(user);
//
//        Mockito.when(userRepository.findAll()).thenReturn(users);
//        Mockito.when(userMapper.toDto(user))
//                .thenReturn(new UserDto(1L, "firstName lastName", new HashSet<>()));
//
//        List<UserDto> userDTOs = userServiceImpl.getAllUsers();
//
//        Assert.assertEquals(1, userDTOs.size());
//    }
//
//    @Test
//    @DisplayName("Should return the user when the username is already taken")
//    void createNewUserWhenUsernameIsAlreadyTaken() {
//        UserCreationDto userCreationDTO =
//                new UserCreationDto(
//                        "firstName", "lastName", "username", "password", new HashSet<>());
//        User user = new User(1L, "firstName", "lastName", "username", "password", new HashSet<>());
//        UserDto userDTO = new UserDto(1L, "firstName lastName", new HashSet<>());
//
//        Mockito.when(passwordEncoder.encode(any())).thenReturn("password");
//        Mockito.when(userRepository.findByUsername(userCreationDTO.getUsername())).thenReturn(user);
//        Mockito.when(userMapper.toUser(userCreationDTO)).thenReturn(user);
//        Mockito.when(userMapper.toDto(user)).thenReturn(userDTO);
//
//        UserDto result = userServiceImpl.createNewUser(userCreationDTO);
//
//        Assert.assertEquals(result, userDTO);
//    }
//
//    @Test
//    @DisplayName("Should return the user when the username is not taken")
//    void createNewUserWhenUsernameIsNotTaken() {
//        UserCreationDto userCreationDTO =
//                new UserCreationDto(
//                        "firstName", "lastName", "username", "password", new HashSet<>());
//
//        User user = new User(1L, "firstName", "lastName", "username", "password", new HashSet<>());
//
//        UserDto userDTO = new UserDto(1L, "firstName lastName", new HashSet<>());
//
//        Mockito.when(passwordEncoder.encode(any())).thenReturn("password");
//        Mockito.when(userRepository.findByUsername(userCreationDTO.getUsername())).thenReturn(null);
//        Mockito.when(userMapper.toUser(userCreationDTO)).thenReturn(user);
//        Mockito.when(userRepository.save(user)).thenReturn(user);
//        Mockito.when(userMapper.toDto(user)).thenReturn(userDTO);
//
//        UserDto result = userServiceImpl.createNewUser(userCreationDTO);
//
//        Assert.assertEquals(result, userDTO);
//    }
//}
