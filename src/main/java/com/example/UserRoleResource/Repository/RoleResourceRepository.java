package com.example.UserRoleResource.Repository;

import com.example.UserRoleResource.Entity.RoleResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleResourceRepository extends JpaRepository<RoleResourceEntity, Long> {


    //It will run on both MySQL and Postgresql
    @Query("SELECT rr.resource.id FROM RoleResourceEntity rr WHERE rr.role.id = :roleId AND rr.isActive = true")
    List<Long> findResourceIdsByRoleIdAndIsActive(Long roleId);


}
