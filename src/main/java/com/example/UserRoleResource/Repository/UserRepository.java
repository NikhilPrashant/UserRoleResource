package com.example.UserRoleResource.Repository;

import com.example.UserRoleResource.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
