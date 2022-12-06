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
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_SUPER_ADMIN");
        UserCreationDto userCreationDTO = new UserCreationDto(
                "john",
                "doe",
                "superadmin",
                "superadmin",
                roles
        );

        // adding edu HQ Admin
        Set<String> roles2 = new HashSet<>();
        roles2.add("ROLE_EDUCATIONAL_INSTITUTE_HQ_ADMIN");
        UserCreationDto userCreationDTO2 = new UserCreationDto(
                "jimmy",
                "doe",
                "hqadmin",
                "hqadmin",
                roles2
        );

        // adding ROLE_REGIONAL_OFFICE_ADMIN
        Set<String> roles3 = new HashSet<>();
        roles3.add("ROLE_REGIONAL_OFFICE_ADMIN");
        UserCreationDto userCreationDTO3 = new UserCreationDto(
                "john",
                "doe",
                "rgadmin",
                "rgadmin",
                roles3
        );

        // adding ROLE_PRINCIPAL
        Set<String> roles4 = new HashSet<>();
        roles4.add("ROLE_PRINCIPAL");
        UserCreationDto userCreationDTO4 = new UserCreationDto(
                "joss",
                "doe",
                "principal",
                "principal",
                roles4
        );

        // adding ROLE_TEACHER
        Set<String> roles5 = new HashSet<>();
        roles5.add("ROLE_TEACHER");
        UserCreationDto userCreationDTO5 = new UserCreationDto(
                "jeff",
                "doe",
                "teacher",
                "teacher",
                roles5
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
