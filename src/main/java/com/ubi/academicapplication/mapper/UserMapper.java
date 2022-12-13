package com.ubi.academicapplication.mapper;

import com.ubi.academicapplication.dto.user.UserCreationDto;
import com.ubi.academicapplication.dto.user.UserDto;
import com.ubi.academicapplication.entity.Role;
import com.ubi.academicapplication.entity.UserInfo;
import com.ubi.academicapplication.service.RoleService;
import com.ubi.academicapplication.util.AutogeneratePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    @Autowired
    RoleService roleService;

    @Autowired
    AutogeneratePassword autogeneratePassword;

    public UserDto toDto(UserInfo user){
        String roleType = null;
        if(user.getRole() != null)  roleType = user.getRole().getRoleType();
        return new UserDto(user.getId(),user.getUsername(),user.getIsEnabled(),roleType);
    }

    public UserInfo toUser(UserCreationDto userCreationDTO) {
        Role role = roleService.getRoleFromString(userCreationDTO.getRoleType());
        return new UserInfo(
                userCreationDTO.getUsername(),
                autogeneratePassword.generate(),
                userCreationDTO.getIsActivate(),
                role
        );
    }
}
