package com.daniel.ms_users.infrastructure.output.jpa.repository;

import com.daniel.ms_users.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
}
