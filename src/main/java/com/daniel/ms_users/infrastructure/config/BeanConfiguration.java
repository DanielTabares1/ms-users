package com.daniel.ms_users.infrastructure.config;

import com.daniel.ms_users.application.util.PasswordEncoderUtil;
import com.daniel.ms_users.application.util.UserValidationImpl;
import com.daniel.ms_users.application.util.UserValidations;
import com.daniel.ms_users.domain.api.IRoleServicePort;
import com.daniel.ms_users.domain.api.IUserServicePort;
import com.daniel.ms_users.domain.spi.IRolePersistencePort;
import com.daniel.ms_users.domain.spi.IUserPersistencePort;
import com.daniel.ms_users.domain.usecase.RoleUseCase;
import com.daniel.ms_users.domain.usecase.UserUseCase;
import com.daniel.ms_users.infrastructure.output.jpa.adapter.RoleJpaAdapter;
import com.daniel.ms_users.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.daniel.ms_users.infrastructure.output.jpa.mapper.RoleEntityMapper;
import com.daniel.ms_users.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.daniel.ms_users.infrastructure.output.jpa.repository.IRoleRepository;
import com.daniel.ms_users.infrastructure.output.jpa.repository.IUserRepository;
import com.daniel.ms_users.infrastructure.security.PasswordEncoderImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;
    private final PasswordEncoder passwordEncoder;


    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }

    @Bean
    public UserValidations userValidations() {
        return new UserValidationImpl();
    }

    @Bean
    public PasswordEncoderUtil passwordEncoderUtil() {
        return new PasswordEncoderImpl(passwordEncoder);
    }


}
