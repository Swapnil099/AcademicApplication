package com.ubi.academicapplication.dto.jwt;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
@AllArgsConstructor
public class JwtResponse {
	long id;
    String token;
    String roleType;
}
