package com.lag.projectmanagement.dto;

public class LoginDto {
    public String token;

    public LoginDto() {
    }

    public LoginDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginDto[" +
                "token='" + token + '\'' +
                ']';
    }
}
