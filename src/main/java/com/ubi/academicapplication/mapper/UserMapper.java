package com.ubi.academicapplication.mapper;

import com.ubi.academicapplication.dto.userdto.UserCreationDTO;
import com.ubi.academicapplication.dto.userdto.UserDTO;
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

    public UserDTO toDto(User user){
        String fullName = user.getFirstName().concat(" ").concat(user.getLastName());
        Set<String> roles = user
                .getRoles()
                .stream()
                .map(Role::getRoleType)
                .collect(Collectors.toSet());

        return new UserDTO(user.getId(),fullName,roles);
    }

    public User toUser(UserCreationDTO userCreationDTO) {
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
