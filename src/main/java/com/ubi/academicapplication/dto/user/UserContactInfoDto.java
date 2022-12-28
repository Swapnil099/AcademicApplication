package com.ubi.academicapplication.dto.user;

import com.ubi.academicapplication.dto.contactinfodto.ContactInfoDto;
import com.ubi.academicapplication.dto.role.RoleDto;
import com.ubi.academicapplication.entity.ContactInfo;
import com.ubi.academicapplication.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserContactInfoDto {
	
	private UserDto userDto;
	private RoleDto roleDto;
	private ContactInfoDto contactInfoDto;

	
}
