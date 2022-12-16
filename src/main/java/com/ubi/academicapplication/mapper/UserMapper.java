package com.ubi.academicapplication.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.dto.contactinfodto.ContactInfoDto;
import com.ubi.academicapplication.dto.user.UserContactInfoDto;
import com.ubi.academicapplication.dto.user.UserCreationDto;
import com.ubi.academicapplication.dto.user.UserDto;
import com.ubi.academicapplication.entity.Role;
import com.ubi.academicapplication.entity.User;
import com.ubi.academicapplication.service.RoleService;
import com.ubi.academicapplication.util.AutogeneratePassword;

@Service
public class UserMapper {

    @Autowired
    RoleService roleService;

    @Autowired
    ContactInfoMapper contactInfoMapper;
    
    @Autowired
    AutogeneratePassword autogeneratePassword;

    public UserDto toDto(User user){
        String roleType = null;
        if(user.getRole() != null)  roleType = user.getRole().getRoleType();
        return new UserDto(user.getId(),user.getUsername(),user.getIsEnabled(),roleType);
    }

    public User toUser(UserCreationDto userCreationDTO) {
        Role role = roleService.getRoleFromString(userCreationDTO.getRoleType());
        return new User(
                userCreationDTO.getUsername(),
                autogeneratePassword.generate(),
                userCreationDTO.getIsActivate(),
                role
        );
    }
   /* public UserContactInfoDto toUserContactInfoDto(User user)
	{
    	UserDto userDto= this.toDto(user);
    	ContactInfoDto contactInfoDto= contactInfoMapper.entityToDto(user.getContactInfo());
		return new UserContactInfoDto(userDto,contactInfoDto);
	}*/
    
    public UserContactInfoDto toUserContactInfoDto(User user)
	{
		return new UserContactInfoDto(user,user.getContactInfo());
	}
}
