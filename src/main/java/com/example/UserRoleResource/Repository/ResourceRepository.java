package com.example.UserRoleResource.Repository;

import com.example.UserRoleResource.Entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {
}
