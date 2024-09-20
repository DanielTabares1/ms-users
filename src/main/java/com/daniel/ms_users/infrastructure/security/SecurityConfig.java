package com.daniel.ms_users.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/v1/clients/register").permitAll()
                        .requestMatchers( "/api/v1/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/owner/**").hasRole("OWNER")
                        .anyRequest()
                        .authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin")
                .password( passwordEncoder().encode("12345"))
                .roles("ADMIN").build());
        manager.createUser(User.withUsername("owner")
                .password( passwordEncoder().encode("12345"))
                .roles("OWNER").build());
        manager.createUser(User.withUsername("employee")
                .password( passwordEncoder().encode("12345"))
                .roles("EMPLOYEE").build());
        manager.createUser(User.withUsername("client")
                .password( passwordEncoder().encode("12345"))
                .roles("CLIENT").build());
        manager.createUser(User.withUsername("none")
                .password( passwordEncoder().encode("12345"))
                .roles("NONE").build());
        return manager;
    }

}
