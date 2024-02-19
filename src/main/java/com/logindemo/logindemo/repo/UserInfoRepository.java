package com.logindemo.logindemo.repo;

import com.logindemo.logindemo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUsername(String username);

    Optional<UserInfo> findByUsernameAndPassword(String username, String password);
}