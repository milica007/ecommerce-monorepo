package com.hc.user_management_service.repository;

import com.hc.user_management_service.model.Role;
import com.hc.user_management_service.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
