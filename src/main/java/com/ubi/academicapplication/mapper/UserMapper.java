package com.ubi.academicapplication.mapper;

import com.ubi.academicapplication.dto.userdto.UserCreationDto;
import com.ubi.academicapplication.dto.userdto.UserDto;
import com.ubi.academicapplication.entity.Role;
import com.ubi.academicapplication.entity.User;
import com.ubi.academicapplication.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserMapper {

    @Autowired
    RoleService roleService;

    public UserDto toDto(User user){
        String fullName = user.getFirstName().concat(" ").concat(user.getLastName());
        Set<String> roles = user
                .getRoles()
                .stream()
                .map(Role::getRoleType)
                .collect(Collectors.toSet());

        return new UserDto(user.getId(),fullName,roles);
    }

    public User toUser(UserCreationDto userCreationDTO) {
        Set<Role> roles = roleService.getRolesFromString(userCreationDTO.getRoles());
        System.out.println("(userMapper) roles set " + roles);
        return new User(
                userCreationDTO.getFirstName(),
                userCreationDTO.getLastName(),
                userCreationDTO.getUsername(),
                userCreationDTO.getPassword(),
                roles
        );
    }
}
