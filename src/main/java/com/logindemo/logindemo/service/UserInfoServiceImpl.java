package com.logindemo.logindemo.service;


import com.logindemo.logindemo.dto.RespondDTO;
import com.logindemo.logindemo.dto.UserDTO;
import com.logindemo.logindemo.model.UserInfo;
import com.logindemo.logindemo.repo.UserInfoRepository;
import com.logindemo.logindemo.security.WebSecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserService {
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserInfo> getAllUser() {
        return userInfoRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        userInfoRepository.deleteById(id);
    }

    @Override
    public void updateUser(Long id, UserDTO userDTO) {
        if (userInfoRepository.existsById(id)) {
            userDTO.setId(id);
            userInfoRepository.save(convertToEntity(userDTO));
        } else {
            throw new NullPointerException("User not found");
        }
    }

    @Override
    public RespondDTO createUser(UserDTO userDTO) {
        if (userInfoRepository.findByUsername(userDTO.getUsername()).isPresent()){
            throw new DuplicateKeyException("User is exist");
        }else {
            String encodePass = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(encodePass);
            userInfoRepository.save(convertToEntity(userDTO));
            return new RespondDTO(200,"SUCCESS","Dang ky thanh cong",convertToEntity(userDTO));
        }
    }

    @Override
    public UserInfo getUserById(Long id) {
        Optional<UserInfo> checkUser = userInfoRepository.findById(id);
        if (checkUser.isPresent()){
            return checkUser.get();
        }else {
            throw new NullPointerException("User not found");
        }
    }

    @Override
    public RespondDTO login(@RequestBody UserDTO userDTO) {
        if (userInfoRepository.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword()).isPresent()){
            return new RespondDTO(200,"OK","Login successfully", convertToEntity(userDTO));
        }else {
            throw new NullPointerException("There are might be username or password is incorrect");
        }
    }

    private UserInfo convertToEntity(UserDTO userDTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userDTO.getId());
        userInfo.setUsername(userDTO.getUsername());
        userInfo.setPassword(userDTO.getPassword());
        userInfo.setFullName(userDTO.getFullName());
        userInfo.setRoles(userDTO.getRoles());
        return userInfo;
    }


}
