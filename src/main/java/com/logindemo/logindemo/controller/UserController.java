package com.logindemo.logindemo.controller;

import com.logindemo.logindemo.dto.RespondDTO;
import com.logindemo.logindemo.dto.UserDTO;
import com.logindemo.logindemo.model.UserInfo;
import com.logindemo.logindemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/all")
    public List<UserDTO> getAllUser(){
        return userService.getAllUser().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @PostMapping("/register")
    public RespondDTO register(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @GetMapping("/login")
    public RespondDTO login(@RequestBody UserDTO userDTO){
        return userService.login(userDTO);
    }


    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello is exception");
    }


    private UserDTO convertToDto(UserInfo userInfo) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userInfo.getId());
        userDTO.setUsername(userInfo.getUsername());
        userDTO.setPassword(userInfo.getPassword());
        userDTO.setFullName(userInfo.getFullName());
        return userDTO;
    }
}
