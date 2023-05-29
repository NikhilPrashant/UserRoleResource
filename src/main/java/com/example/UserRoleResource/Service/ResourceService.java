package com.example.UserRoleResource.Service;

import com.example.UserRoleResource.DTOs.ResourceEntryDto;
import com.example.UserRoleResource.Entity.ResourceEntity;
import com.example.UserRoleResource.Entity.RoleResourceEntity;
import com.example.UserRoleResource.Repository.ResourceRepository;
import com.example.UserRoleResource.Repository.RoleResourceRepository;
import com.example.UserRoleResource.VO.ResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private RoleResourceRepository roleResourceRepository;

    public ResourceEntity addResource(ResourceEntryDto resourceEntryDto) throws Exception {
        ResourceEntity resource = ResourceEntity.builder()
                .name(resourceEntryDto.getName())
                .description(resourceEntryDto.getDescription())
                .isActive(true)
                .build();
        resource = resourceRepository.save(resource);
        return resource;
    }

    public ResourceVO getResourceVO(Long id) throws Exception {
        ResourceEntity resource = resourceRepository.findById(id).get();
        if (!resource.isActive()) throw new Exception("Resource is Inactive");
        ResourceVO resourceVO = new ResourceVO(resource);
        return resourceVO;
    }

    public Long setResourceInactive(Long resourceId) throws Exception {
        ResourceEntity resource = resourceRepository.findById(resourceId).get();
        resource.setActive(false);
        for (RoleResourceEntity roleResource : resource.getRoleResourceEntities()) roleResource.setActive(false);
        resourceRepository.save(resource);
        return resourceId;
    }

    public Long hardDeleteResources(Long resourceId) throws Exception {
        resourceRepository.deleteById(resourceId);
        return resourceId;
    }
}
