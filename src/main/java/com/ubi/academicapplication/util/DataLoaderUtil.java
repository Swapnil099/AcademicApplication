package com.ubi.academicapplication.util;

import com.ubi.academicapplication.dto.role.RoleCreationDto;
import com.ubi.academicapplication.dto.user.UserDto;
import com.ubi.academicapplication.entity.Role;
import com.ubi.academicapplication.entity.UserInfo;
import com.ubi.academicapplication.repository.UserRepository;
import com.ubi.academicapplication.service.RoleService;
import com.ubi.academicapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderUtil implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        Role roleSuperAdmin = roleService.getRoleByRoleType("ROLE_SUPER_ADMIN");
        Role roleEducationHQAdmin = roleService.getRoleByRoleType("ROLE_EDUCATIONAL_INSTITUTE_HQ_ADMIN");
        Role roleRegionalAdmin = roleService.getRoleByRoleType("ROLE_REGIONAL_OFFICE_ADMIN");
        Role rolePrincipal = roleService.getRoleByRoleType("ROLE_PRINCIPAL");
        Role roleTeacher = roleService.getRoleByRoleType("ROLE_TEACHER");

        UserInfo superAdminUser = new UserInfo("superadmin",passwordEncoder.encode("superadmin"),true,roleSuperAdmin);
        UserInfo hqAdminUser = new UserInfo("hqadmin",passwordEncoder.encode("hqadmin"),true,roleEducationHQAdmin);
        UserInfo rgAdminUser = new UserInfo("rgadmin",passwordEncoder.encode("rgadmin"),true,roleRegionalAdmin);
        UserInfo principalUser = new UserInfo("principal",passwordEncoder.encode("principal"),true,rolePrincipal);
        UserInfo teacherUser = new UserInfo("teacher",passwordEncoder.encode("teacher"),true,roleTeacher);


        userRepository.save(superAdminUser);
        userRepository.save(hqAdminUser);
        userRepository.save(rgAdminUser);
        userRepository.save(principalUser);
        userRepository.save(teacherUser);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserDto userDTO = userService.getUserByUsername("superadmin");
        if(userDTO != null) return;
        loadRolesInDatabase();
        loadUserInDatabase();
    }
}
