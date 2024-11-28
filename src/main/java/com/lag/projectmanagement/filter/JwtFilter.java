package com.lag.projectmanagement.filter;

import com.lag.projectmanagement.dto.CustomUserDetails;
import com.lag.projectmanagement.entity.RoleEntity;
import com.lag.projectmanagement.entity.AccountEntity;
import com.lag.projectmanagement.repository.RoleRepository;
import com.lag.projectmanagement.repository.UserAuthRepository;
import com.lag.projectmanagement.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtService.getUsername(jwt);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails userDetails = getUserDetailsByEmail(userEmail);

        if (jwtService.validateToken(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private CustomUserDetails getUserDetailsByEmail(String email) throws UsernameNotFoundException {
        AccountEntity accountEntity = userAuthRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User email not found"));
        Set<RoleEntity> simpleGrantedAuthorities = roleRepository.findPersonRolesByEmail(email);

        List<SimpleGrantedAuthority> authorityList = simpleGrantedAuthorities.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).toList();

        return new CustomUserDetails(
                accountEntity.getId(),
                accountEntity.getEmail(),
                accountEntity.getPassword(),
                accountEntity.isAccountNoExpired(),
                accountEntity.isCredentialNoExpired(),
                accountEntity.isAccountNoLocked(),
                accountEntity.isEnabled(),
                authorityList
        );
    }
}
