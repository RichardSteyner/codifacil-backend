package com.codifacil.codifacilbackend.services;


import com.codifacil.codifacilbackend.models.dto.AuthRequest;
import com.codifacil.codifacilbackend.models.dto.AuthResponse;

public interface AuthService {

    public AuthResponse authenticate(AuthRequest authRequest);

}
