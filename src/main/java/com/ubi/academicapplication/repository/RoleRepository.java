package com.ubi.academicapplication.repository;

import com.ubi.academicapplication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByRoleName(String roleName);

    Role getRoleByRoleType(String roleType);
}
