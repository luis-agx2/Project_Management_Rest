package com.lag.projectmanagement.service.impl;

import com.lag.projectmanagement.entity.AccountEntity;
import com.lag.projectmanagement.repository.UserAuthRepository;
import com.lag.projectmanagement.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountEntity userAuth = userAuthRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User email not found"));

        return new User(
                userAuth.getEmail(),
                userAuth.getPassword(),
                userAuth.isEnabled(),
                userAuth.isAccountNoExpired(),
                userAuth.isCredentialNoExpired(),
                userAuth.isAccountNoLocked(),
                new ArrayList<>()
        );
    }
}
