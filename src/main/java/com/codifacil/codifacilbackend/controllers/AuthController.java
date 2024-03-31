package com.codifacil.codifacilbackend.controllers;

import com.codifacil.codifacilbackend.models.dto.AuthRequest;
import com.codifacil.codifacilbackend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"https://www.codifacil.club/", "http://www.codifacil.club/", "http://localhost:4200"})
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    private ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }

}
