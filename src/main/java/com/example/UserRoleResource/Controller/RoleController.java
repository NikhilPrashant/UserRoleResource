package com.example.UserRoleResource.Controller;

import com.example.UserRoleResource.DTOs.RoleEntryDto;
import com.example.UserRoleResource.Entity.RoleEntity;
import com.example.UserRoleResource.Service.RoleService;
import com.example.UserRoleResource.VO.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/addRole")
    public ResponseEntity<RoleEntity> addRole(@RequestBody RoleEntryDto roleEntryDto) {
        try {
            RoleEntity role = roleService.addRole(roleEntryDto);
            return new ResponseEntity<>(role, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/getRole")
    public ResponseEntity<RoleVO> getRole(@RequestParam Long roleId) {
        try {
            RoleVO roleVO = roleService.getRoleVO(roleId);
            return new ResponseEntity<>(roleVO, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/addResourceToRole")
    public ResponseEntity<Long> addResourceToRole(@RequestParam Long roleId, @RequestParam Long resourceId) {
        try {
            Long response = roleService.addResourceToRole(roleId, resourceId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/removeResourceFromRole")
    public ResponseEntity<Long> removeResourceFromRole(@RequestParam Long roleId, @RequestParam Long resourceId) {
        try {
            Long response = roleService.removeResourceFromRole(roleId, resourceId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/setRoleInactive")
    public ResponseEntity<Long> setRoleInactive(@RequestParam Long roleId) {
        try {
            Long response = roleService.setRoleInactive(roleId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/deleteRole")
    public ResponseEntity<Long> deleteRole(@RequestParam Long roleId) {
        try {
            Long response = roleService.hardDeleteRole(roleId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
