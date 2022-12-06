package com.ubi.academicapplication.dto.jwtdto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @RequiredArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    String token;
    String roleType;
}
