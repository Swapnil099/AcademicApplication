package com.ubi.academicapplication.dto.jwtdto;

import lombok.*;

@Getter
@Setter @AllArgsConstructor @NoArgsConstructor
public class LoginCredentialDTO {
    String username;
    String password;
}
