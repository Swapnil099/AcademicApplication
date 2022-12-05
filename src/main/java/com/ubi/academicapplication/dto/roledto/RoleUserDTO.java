package com.ubi.academicapplication.dto.roledto;

import lombok.*;


@Data @RequiredArgsConstructor
@AllArgsConstructor
public class RoleUserDTO {
    Long id;
    String firstName;
    String lastName;
}
