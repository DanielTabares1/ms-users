package com.daniel.ms_users.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = EntityConstants.ROLE_TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;

    @Override
    public String getAuthority() {
        return EntityConstants.ROLE_PREFIX + this.name;
    }
}
