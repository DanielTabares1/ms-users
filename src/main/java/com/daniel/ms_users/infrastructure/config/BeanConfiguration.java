package com.daniel.ms_users.infrastructure.config;

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
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;


    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort(){
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IRoleServicePort roleServicePort(){
        return new RoleUseCase(rolePersistencePort());
    }


}
