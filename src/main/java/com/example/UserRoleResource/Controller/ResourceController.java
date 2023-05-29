package com.example.UserRoleResource.Controller;

import com.example.UserRoleResource.DTOs.ResourceEntryDto;
import com.example.UserRoleResource.Entity.ResourceEntity;
import com.example.UserRoleResource.Service.ResourceService;
import com.example.UserRoleResource.VO.ResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @PostMapping("/addResource")
    public ResponseEntity<ResourceEntity> addResource(@RequestBody ResourceEntryDto resourceEntryDto) {
        try {
            ResourceEntity resource = resourceService.addResource(resourceEntryDto);
            return new ResponseEntity<>(resource, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/getResource")
    public ResponseEntity<ResourceVO> getResource(@RequestParam Long resourceId) {
        try {
            ResourceVO resourceVO = resourceService.getResourceVO(resourceId);
            return new ResponseEntity<>(resourceVO, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/setResourceInactive")
    public ResponseEntity<Long> setResourceInactive(@RequestParam Long resourceId) {
        try {
            Long response = resourceService.setResourceInactive(resourceId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/deleteResource")
    public ResponseEntity<Long> deleteResources(@RequestParam Long resourceId) {
        try {
            Long response = resourceService.hardDeleteResources(resourceId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
