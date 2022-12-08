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
        String roleType = null;
        if(user.getRole() != null)  roleType = user.getRole().getRoleType();
        return new UserDto(user.getId(),user.getUsername(),roleType);
    }

    public User toUser(UserCreationDto userCreationDTO) {
        Role role = roleService.getRoleFromString(userCreationDTO.getRoleType());
        return new User(
                userCreationDTO.getUsername(),
                userCreationDTO.getPassword(),
                role
        );
    }
}
