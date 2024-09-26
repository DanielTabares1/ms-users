package com.daniel.ms_users.infrastructure.security;

import com.daniel.ms_users.application.dto.AuthenticationRequest;
import com.daniel.ms_users.application.dto.AuthenticationResponse;
import com.daniel.ms_users.domain.api.IJwtServicePort;
import com.daniel.ms_users.domain.exception.ErrorMessages;
import com.daniel.ms_users.infrastructure.output.jpa.entity.UserEntity;
import com.daniel.ms_users.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {

    private final IUserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final IJwtServicePort jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.USER_NOT_FOUND_BY_EMAIL.getMessage(request.getEmail())));

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(SecurityConstants.ROLE_NAME_CLAIM, user.getRole().getName());

        String jwtToken = jwtService.generateToken(extraClaims,user);
        return new AuthenticationResponse(jwtToken);
    }
}
