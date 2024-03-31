package com.codifacil.codifacilbackend.models.repository;

import com.codifacil.codifacilbackend.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username=?1 and u.password=?2 and u.vigencia=true")
    public Optional<User> login(String username, String password);

    public Optional<User> findByUsername(String username);

}
