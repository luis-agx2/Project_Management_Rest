package com.lag.projectmanagement.service;

import com.lag.projectmanagement.dto.LoginDto;
import com.lag.projectmanagement.dto.request.LoginRequest;
import com.lag.projectmanagement.dto.request.RegisterRequest;
import com.lag.projectmanagement.exception.NotFoundException;
import com.lag.projectmanagement.exception.ResourceNotAvailableException;

public interface AuthService {
    LoginDto login(LoginRequest loginRequest) throws NotFoundException;

    void register(RegisterRequest registerRequest) throws NotFoundException, ResourceNotAvailableException;
}
