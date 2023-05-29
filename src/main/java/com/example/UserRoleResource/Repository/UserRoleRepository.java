package com.example.UserRoleResource.Repository;

import com.example.UserRoleResource.Entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {


    //It will run on both MySQL and Postgresql
    @Query("SELECT ur.role.id FROM UserRoleEntity ur WHERE ur.user.id = :userId AND ur.isActive = true")
    List<Long> findRoleIdsByUserIdAndIsActive(Long userId);

}
