package com.example.UserRoleResource.VO;

import com.example.UserRoleResource.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {

    Long id;

    String name;

    String email;

    List<RoleVO> roleVOS;

    boolean isActive;

    public UserVO(UserEntity user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.isActive = user.isActive();
    }

}
