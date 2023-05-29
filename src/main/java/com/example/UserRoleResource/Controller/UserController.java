package com.example.UserRoleResource.Controller;

import com.example.UserRoleResource.DTOs.UserEntryDto;
import com.example.UserRoleResource.Entity.UserEntity;
import com.example.UserRoleResource.Service.UserService;
import com.example.UserRoleResource.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntryDto userEntryDto) {
        try {
            System.out.println(userEntryDto);
            UserEntity user = userService.addUser(userEntryDto);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserVO> getUser(@RequestParam Long userId) {
        try {
            UserVO userVO = userService.getUserVO(userId);
            return new ResponseEntity<>(userVO, HttpStatus.FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/addRoleToUser")
    public ResponseEntity<Long> addRoleToUser(@RequestParam Long userId, @RequestParam Long roleId) {
        try {
            Long response = userService.addRoleToUser(userId, roleId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/removeRoleFromUser")
    public ResponseEntity<Long> removeRoleFromUser(@RequestParam Long userId, @RequestParam Long roleId) {
        try {
            Long response = userService.removeRoleFromUser(userId, roleId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/setUserInactive")
    public ResponseEntity<Long> setUserInactive(@RequestParam Long userId) {
        try {
            Long response = userService.setUserInactive(userId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Long> deleteUser(@RequestParam Long userId) {
        try {
            Long response = userService.hardDeleteUser(userId);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
