package com.example.motorzone.models.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponseDTO {
    private String token;

    @JsonProperty("expires_in")
    private long expiresIn;

    public String getToken() {
        return token;
    }

    public LoginResponseDTO setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponseDTO setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

}