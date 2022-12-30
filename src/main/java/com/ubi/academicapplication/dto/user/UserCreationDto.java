package com.ubi.academicapplication.dto.user;

import com.ubi.academicapplication.dto.contactinfodto.ContactInfoDto;
import com.ubi.academicapplication.entity.ContactInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
@AllArgsConstructor
public class UserCreationDto {
    private String username;
    private Boolean isActivate;
    private String roleType;
    
    private ContactInfoDto contactInfoDto;
}
