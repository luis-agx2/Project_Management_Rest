package com.lag.projectmanagement.controller;

import com.lag.projectmanagement.dto.ErrorResponseDto;
import com.lag.projectmanagement.dto.request.LoginRequest;
import com.lag.projectmanagement.dto.request.RegisterRequest;
import com.lag.projectmanagement.exception.NotFoundException;
import com.lag.projectmanagement.exception.ResourceNotAvailableException;
import com.lag.projectmanagement.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/auth/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) throws NotFoundException, BadCredentialsException {
        try {
            return ResponseEntity.ok(authService.login(loginRequest));
        } catch (BadCredentialsException e) {
            ErrorResponseDto errorResponse = new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), "Bad Credentials");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PostMapping("/auth/register")
    @PreAuthorize("permitAll()")
    @Validated
    public ResponseEntity<?> signUp(@Valid @RequestBody RegisterRequest registerRequest) throws NotFoundException, ResourceNotAvailableException {
        authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
