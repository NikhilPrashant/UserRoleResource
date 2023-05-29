package com.example.UserRoleResource.Repository;

import com.example.UserRoleResource.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
