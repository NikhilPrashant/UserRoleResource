package com.example.UserRoleResource.Service;

import com.example.UserRoleResource.DTOs.RoleEntryDto;
import com.example.UserRoleResource.Entity.ResourceEntity;
import com.example.UserRoleResource.Entity.RoleEntity;
import com.example.UserRoleResource.Entity.RoleResourceEntity;
import com.example.UserRoleResource.Entity.UserRoleEntity;
import com.example.UserRoleResource.Repository.ResourceRepository;
import com.example.UserRoleResource.Repository.RoleRepository;
import com.example.UserRoleResource.Repository.RoleResourceRepository;
import com.example.UserRoleResource.VO.ResourceVO;
import com.example.UserRoleResource.VO.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private RoleResourceRepository roleResourceRepository;

    public RoleEntity addRole(RoleEntryDto roleEntryDto) throws Exception {
        RoleEntity role = RoleEntity.builder()
                .name(roleEntryDto.getName())
                .description(roleEntryDto.getDescription())
                .isActive(true)
                .build();
        role = roleRepository.save(role);
        return role;
    }

    public RoleVO getRoleVO(Long roleId) throws Exception {
        RoleEntity role = roleRepository.findById(roleId).get();
        if (!role.isActive()) throw new Exception("Role Is Inactive");
        RoleVO roleVO = new RoleVO(role);
        roleVO.setResourceVOS(getResourceVOs(roleId));
        return roleVO;
    }

    private List<ResourceVO> getResourceVOs(Long roleId) throws Exception {
        List<ResourceVO> resourceVOList = new ArrayList<>();
        List<Long> resourceIds = roleResourceRepository.findResourceIdsByRoleIdAndIsActive(roleId);
        for (Long resourceId : resourceIds) {
            ResourceVO resourceVO = resourceService.getResourceVO(resourceId);
            resourceVOList.add(resourceVO);
        }
        return resourceVOList;
    }

    public Long addResourceToRole(Long roleId, Long resourceId) throws Exception {
        RoleEntity role = roleRepository.findById(roleId).get();
        ResourceEntity resource = resourceRepository.findById(resourceId).get();
        RoleResourceEntity roleResource = RoleResourceEntity.builder()
                .role(role)
                .resource(resource)
                .isActive(true)
                .build();
        roleResourceRepository.save(roleResource);
        return resourceId;
    }

    public Long removeResourceFromRole(Long roleId, Long resourceId) throws Exception {
        RoleEntity role = roleRepository.findById(roleId).get();
        ResourceEntity resource = resourceRepository.findById(resourceId).get();
        boolean b = false;
        for (RoleResourceEntity roleResource : role.getRoleResourceEntities()) {
            if (roleResource.getRole().equals(role) && roleResource.getResource().equals(resource)) {
                roleResource.setActive(false);
                b = true;
                break;
            }
        }
        if (!b) throw new Exception("This resource is not alloted to this role");
        roleRepository.save(role);
        return resourceId;
    }

    public Long setRoleInactive(Long roleId) throws Exception {
        RoleEntity role = roleRepository.findById(roleId).get();
        role.setActive(false);
        for (UserRoleEntity userRole : role.getUserRoleEntities()) userRole.setActive(false);
        for (RoleResourceEntity roleResource : role.getRoleResourceEntities()) roleResource.setActive(false);
        roleRepository.save(role);
        return roleId;
    }

    public Long hardDeleteRole(Long roleId) throws Exception {
        roleRepository.deleteById(roleId);
        return roleId;
    }
}
