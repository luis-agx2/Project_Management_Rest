package com.lag.projectmanagement.service.impl;

import com.lag.projectmanagement.dto.LoginDto;
import com.lag.projectmanagement.dto.request.LoginRequest;
import com.lag.projectmanagement.dto.request.RegisterRequest;
import com.lag.projectmanagement.entity.PersonEntity;
import com.lag.projectmanagement.entity.RoleEntity;
import com.lag.projectmanagement.entity.AccountEntity;
import com.lag.projectmanagement.exception.NotFoundException;
import com.lag.projectmanagement.exception.ResourceNotAvailableException;
import com.lag.projectmanagement.mapper.AuthMapper;
import com.lag.projectmanagement.repository.AccountRepository;
import com.lag.projectmanagement.repository.PersonRepository;
import com.lag.projectmanagement.repository.RoleRepository;
import com.lag.projectmanagement.repository.UserAuthRepository;
import com.lag.projectmanagement.service.AuthService;
import com.lag.projectmanagement.service.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private PersonRepository personRepository;

    @Override
    public LoginDto login(LoginRequest loginRequest) throws NotFoundException, BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));

        AccountEntity accountEntity = userAuthRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new NotFoundException("User email not found"));

        UserDetails userDetails = buildUserDetails(accountEntity);
        String jwtToken = jwtService.generateToken(userDetails);

        return new LoginDto(jwtToken);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void register(RegisterRequest registerRequest) throws NotFoundException, ResourceNotAvailableException {
        PersonEntity personEntity = authMapper.toPersonEntity(registerRequest);
        AccountEntity accountEntity = authMapper.toAccountEntity(registerRequest);
        accountEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        accountEntity.setAccountNoExpired(true);
        accountEntity.setAccountNoLocked(true);
        accountEntity.setCredentialNoExpired(true);
        accountEntity.setEnabled(true);

        AccountEntity accountDb = accountRepository.save(accountEntity);
        RoleEntity roleEntity = roleRepository.findById(registerRequest.getRoleId()).orElseThrow(() -> new NotFoundException("Role not found"));

        personEntity.setAccount(accountDb);
        personEntity.setRoles(Set.of(roleEntity));

        personRepository.save(personEntity);
    }

    private UserDetails buildUserDetails(AccountEntity userEntity) {
        Set<RoleEntity> roleEntities = roleRepository.findPersonRolesByEmail(userEntity.getEmail());
        List<SimpleGrantedAuthority> authorities = buildAuthorities(roleEntities);

        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorities
        );
    }

    private List<SimpleGrantedAuthority> buildAuthorities(Set<RoleEntity> roleEntities) {
        return roleEntities.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }
}
