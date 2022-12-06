package com.ubi.academicapplication.dto.jwtdto;

import lombok.*;

@Getter
@Setter @AllArgsConstructor @NoArgsConstructor
public class LoginCredentialDto {
    String username;
    String password;
}
