package com.example.UserRoleResource.Service;

import com.example.UserRoleResource.DTOs.UserEntryDto;
import com.example.UserRoleResource.Entity.RoleEntity;
import com.example.UserRoleResource.Entity.UserEntity;
import com.example.UserRoleResource.Entity.UserRoleEntity;
import com.example.UserRoleResource.Repository.RoleRepository;
import com.example.UserRoleResource.Repository.UserRepository;
import com.example.UserRoleResource.Repository.UserRoleRepository;
import com.example.UserRoleResource.VO.RoleVO;
import com.example.UserRoleResource.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    RoleService roleService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserEntity addUser(UserEntryDto userEntryDto) throws Exception {
        UserEntity user = UserEntity.builder()
                .name(userEntryDto.getName())
                .email(userEntryDto.getEmail())
                .isActive(true)
                .build();
        user = userRepository.save(user);
        return user;
    }

    public UserVO getUserVO(Long userId) throws Exception {
        UserEntity user = userRepository.findById(userId).get();
        if (!user.isActive()) throw new Exception("User Is Inactive");
        UserVO userVO = new UserVO(user);
        userVO.setRoleVOS(getRoleVOList(userId));
        return userVO;
    }

    private List<RoleVO> getRoleVOList(Long userId) throws Exception {
        List<RoleVO> roleVOList = new ArrayList<>();
        List<Long> roleIds = userRoleRepository.findRoleIdsByUserIdAndIsActive(userId);
        System.out.println(roleIds);
        for (Long roleId : roleIds) {
            RoleVO roleVO = roleService.getRoleVO(roleId);
            roleVOList.add(roleVO);
        }
        return roleVOList;
    }

    public Long addRoleToUser(Long userId, Long roleId) throws Exception {
        UserEntity user = userRepository.findById(userId).get();
        if (!user.isActive()) throw new Exception("User is Inactive");
        RoleEntity role = roleRepository.findById(roleId).get();
        if (!role.isActive()) throw new Exception("Role is Inactive");
        UserRoleEntity userRole = UserRoleEntity.builder()
                .user(user)
                .role(role)
                .isActive(true)
                .build();
        userRoleRepository.save(userRole);
        return roleId;
    }

    public Long removeRoleFromUser(Long userId, Long roleId) throws Exception {
        UserEntity user = userRepository.findById(userId).get();
        if (!user.isActive()) throw new Exception("User is Inactive");
        RoleEntity role = roleRepository.findById(roleId).get();
        if (!role.isActive()) throw new Exception("Role is Inactive");
        boolean b = false;
        //Loop needs to be replaced with JPA
        for (UserRoleEntity userRole : user.getUserRoleEntities()) {
            if (userRole.getUser().equals(user) && userRole.getRole().equals(role)) {
                userRole.setActive(false);
                b = true;
                break;
            }
        }
        if (!b) throw new Exception("This resource is not alloted to this role");
        roleRepository.save(role);
        return roleId;
    }

    public Long setUserInactive(Long userId) throws Exception {
        UserEntity user = userRepository.findById(userId).get();
        if (!user.isActive()) throw new Exception("User is Already Inactive");
        user.setActive(false);
        for (UserRoleEntity userRole : user.getUserRoleEntities()) userRole.setActive(false);
        userRepository.save(user);
        return userId;
    }

    public Long hardDeleteUser(Long userId) throws Exception {
        userRepository.deleteById(userId);
        return userId;
    }
}
