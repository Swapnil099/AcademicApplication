package com.ubi.academicapplication.dto.jwt;

import lombok.*;

@Getter
@Setter @AllArgsConstructor @NoArgsConstructor
public class LoginCredentialDto {
    String username;
    String password;
}
