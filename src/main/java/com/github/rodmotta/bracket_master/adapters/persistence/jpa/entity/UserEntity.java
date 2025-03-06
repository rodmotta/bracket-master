package com.github.rodmotta.bracket_master.adapters.persistence.jpa.entity;

import com.github.rodmotta.bracket_master.core.model.User;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public User toDomain() {
        return new User(id, name, email);
    }
}
