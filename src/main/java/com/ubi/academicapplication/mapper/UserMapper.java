package com.ubi.academicapplication.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubi.academicapplication.dto.contactinfodto.ContactInfoDto;
import com.ubi.academicapplication.dto.role.RoleDto;
import com.ubi.academicapplication.dto.user.UserContactInfoDto;
import com.ubi.academicapplication.dto.user.UserCreationDto;
import com.ubi.academicapplication.dto.user.UserDto;
import com.ubi.academicapplication.entity.Role;
import com.ubi.academicapplication.entity.User;
import com.ubi.academicapplication.repository.ContactInfoRepository;
import com.ubi.academicapplication.service.ContactInfoService;
import com.ubi.academicapplication.service.RoleService;
import com.ubi.academicapplication.util.AutogeneratePassword;

@Service
public class UserMapper {

	@Autowired
	RoleService roleService;

	@Autowired
	ContactInfoMapper contactInfoMapper;

	@Autowired
	ContactInfoService contactInfoService;

	@Autowired
	RoleMapper roleMapper;

	@Autowired
	AutogeneratePassword autogeneratePassword;

	@Autowired
	private ContactInfoRepository contactInfoRepository;

	public UserDto toDto(User user){
		String roleType = null;
		if(user.getRole() != null)  roleType = user.getRole().getRoleType();
		return new UserDto(user.getId(),user.getUsername(),user.getIsEnabled(),roleType);
	}

	public UserCreationDto toUserCreationDto(User user){
		String roleType = null;
		if(user.getRole() != null)  roleType = user.getRole().getRoleType();
		ContactInfoDto contactInfoDto=contactInfoMapper.entityToDto(user.getContactInfo());
		return new UserCreationDto(user.getUsername(),user.getIsEnabled(),roleType,contactInfoDto);
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

	public UserContactInfoDto toUserContactInfoDto(User user)
	{
		UserDto userDto= this.toDto(user);
		RoleDto roleDto = null;
		if(user.getRole() != null){
			roleDto=roleMapper.toDto(user.getRole());
		}

		ContactInfoDto contactInfoDto= null;
		if(user.getContactInfo() != null)
		{
			contactInfoDto = contactInfoMapper.entityToDto(user.getContactInfo());
		}
		return new UserContactInfoDto(userDto,roleDto,contactInfoDto);
	}
}
