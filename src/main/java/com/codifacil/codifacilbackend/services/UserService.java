package com.codifacil.codifacilbackend.services;

import com.codifacil.codifacilbackend.common.CommonService;
import com.codifacil.codifacilbackend.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public interface UserService extends CommonService<User, Long> {

    public Optional<User> login(String username, String password);

}
