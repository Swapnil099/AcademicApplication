package com.ubi.academicapplication.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data @RequiredArgsConstructor @AllArgsConstructor
public class UserDTO {
    private Long id;
    private String fullName;
    private Set<String> roles;
}
