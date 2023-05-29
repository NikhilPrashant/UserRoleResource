package com.example.UserRoleResource.VO;

import com.example.UserRoleResource.Entity.ResourceEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceVO {

    Long id;

    String name;

    String description;

    LocalDate dateCreated;

    LocalDate dateUpdated;

    boolean isActive;

    public ResourceVO(ResourceEntity resourceEntity) {
        this.id = resourceEntity.getId();
        this.name = resourceEntity.getName();
        this.description = resourceEntity.getDescription();
        this.dateCreated = resourceEntity.getDateCreated();
        this.dateUpdated = resourceEntity.getDateUpdated();
        this.isActive = resourceEntity.isActive();
    }
}
