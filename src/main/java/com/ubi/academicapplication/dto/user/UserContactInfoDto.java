package com.ubi.academicapplication.dto.user;

import com.ubi.academicapplication.dto.contactinfodto.ContactInfoDto;
import com.ubi.academicapplication.entity.ContactInfo;
import com.ubi.academicapplication.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserContactInfoDto {
	
	//private UserDto userDto;
	//private ContactInfoDto contactInfoDto;

	private User user;
	private ContactInfo contactInfo;
}
