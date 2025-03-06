package com.github.rodmotta.bracket_master.core.ports;

import com.github.rodmotta.bracket_master.core.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findByEmail(String email);
    void save(User user);
}
