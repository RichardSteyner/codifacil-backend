package com.codifacil.codifacilbackend.services;

import com.codifacil.codifacilbackend.common.CommonServiceImpl;
import com.codifacil.codifacilbackend.models.entity.User;
import com.codifacil.codifacilbackend.models.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserServiceImpl extends CommonServiceImpl<User, Long, UserRepository>
        implements UserService {

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User insert(User entity) {
        Optional<User> oUser = this.repository.findByUsername(entity.getUsername());
        if(oUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User %s already exist", entity.getUsername()));
        }
        String passwordEncode = encoder.encode(entity.getPassword());
        entity.setPassword(passwordEncode);

        return  this.repository.save(entity);
    }

    @Override
    public Optional<User> login(String username, String password) {
        return repository.login(username, password);
    }
}
