package com.example.UserRoleResource.VO;

import com.example.UserRoleResource.Entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleVO {

    Long id;

    String name;

    String description;

    LocalDate dateCreated;

    LocalDate dateUpdated;

    List<ResourceVO> resourceVOS;

    boolean isActive;

    public RoleVO(RoleEntity role) {
        this.id = role.getId();
        this.name = role.getName();
        this.description = role.getDescription();
        this.dateCreated = role.getDateCreated();
        this.dateUpdated = role.getDateUpdated();
        this.isActive = role.isActive();
    }


}
