package com.ubi.academicapplication.util;

import com.ubi.academicapplication.dto.roledto.RoleCreationDto;
import com.ubi.academicapplication.dto.userdto.UserCreationDto;
import com.ubi.academicapplication.dto.userdto.UserDto;
import com.ubi.academicapplication.service.RoleService;
import com.ubi.academicapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoaderUtil implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private void loadRolesInDatabase(){
        RoleCreationDto roleSuperAdmin = new RoleCreationDto("Super Admin","ROLE_SUPER_ADMIN");
        RoleCreationDto roleEducationHQAdmin = new RoleCreationDto("Educational Institute HQ ADMIN","ROLE_EDUCATIONAL_INSTITUTE_HQ_ADMIN");
        RoleCreationDto roleRegionalAdmin = new RoleCreationDto("Regional Admin","ROLE_REGIONAL_OFFICE_ADMIN");
        RoleCreationDto rolePrincipal = new RoleCreationDto("Principal","ROLE_PRINCIPAL");
        RoleCreationDto roleTeacher = new RoleCreationDto("Teacher","ROLE_TEACHER");
        roleService.createRole(roleSuperAdmin);
        roleService.createRole(roleEducationHQAdmin);
        roleService.createRole(roleRegionalAdmin);
        roleService.createRole(rolePrincipal);
        roleService.createRole(roleTeacher);
    }

    private void loadUserInDatabase() {
        // adding super admin
        UserCreationDto userCreationDTO = new UserCreationDto(
                "superadmin",
                "superadmin",
                "ROLE_SUPER_ADMIN"
        );

        // adding edu HQ Admin
        UserCreationDto userCreationDTO2 = new UserCreationDto(
                "hqadmin",
                "hqadmin",
                "ROLE_EDUCATIONAL_INSTITUTE_HQ_ADMIN"
        );

        // adding ROLE_REGIONAL_OFFICE_ADMIN
        UserCreationDto userCreationDTO3 = new UserCreationDto(
                "rgadmin",
                "rgadmin",
                "ROLE_REGIONAL_OFFICE_ADMIN"
        );

        // adding ROLE_PRINCIPAL
        UserCreationDto userCreationDTO4 = new UserCreationDto(
                "principal",
                "principal",
                "ROLE_PRINCIPAL"
        );

        // adding ROLE_TEACHER
        UserCreationDto userCreationDTO5 = new UserCreationDto(
                "teacher",
                "teacher",
                "ROLE_TEACHER"
        );

        userService.createNewUser(userCreationDTO);
        userService.createNewUser(userCreationDTO2);
        userService.createNewUser(userCreationDTO3);
        userService.createNewUser(userCreationDTO4);
        userService.createNewUser(userCreationDTO5);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserDto userDTO = userService.getUserByUsername("superadmin");
        if(userDTO != null) return;
        loadRolesInDatabase();
        loadUserInDatabase();
    }
}
