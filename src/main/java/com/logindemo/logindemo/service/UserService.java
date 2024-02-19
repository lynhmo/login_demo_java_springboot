package com.logindemo.logindemo.service;

import com.logindemo.logindemo.dto.RespondDTO;
import com.logindemo.logindemo.dto.UserDTO;
import com.logindemo.logindemo.model.UserInfo;

import java.util.List;

public interface UserService {
    UserInfo getUserById(Long id);

    List<UserInfo> getAllUser();

    RespondDTO createUser(UserDTO userDTO);

    void deleteUserById(Long id);

    void updateUser(Long id, UserDTO userDTO);

    RespondDTO login(UserDTO userDTO);
}
