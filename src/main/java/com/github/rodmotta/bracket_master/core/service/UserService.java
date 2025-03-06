package com.github.rodmotta.bracket_master.core.service;

import com.github.rodmotta.bracket_master.core.model.User;
import com.github.rodmotta.bracket_master.core.ports.IdentityProviderPort;
import com.github.rodmotta.bracket_master.core.ports.JwtServicePort;
import com.github.rodmotta.bracket_master.core.ports.UserRepositoryPort;

import java.util.Optional;

public class UserService {
    private final IdentityProviderPort identityProviderPort;
    private final UserRepositoryPort userRepositoryPort;
    private final JwtServicePort jwtServicePort;

    public UserService(IdentityProviderPort identityProviderPort, UserRepositoryPort userRepositoryPort, JwtServicePort jwtServicePort) {
        this.identityProviderPort = identityProviderPort;
        this.userRepositoryPort = userRepositoryPort;
        this.jwtServicePort = jwtServicePort;
    }

    public void save(User user) {
        var existingUser = findByEmail(user.getEmail());
        if (existingUser.isPresent()) return;

        userRepositoryPort.save(user);
    }

    public String authenticate(String authCode) {
        var accessToken = identityProviderPort.getAccessToken(authCode);
        var user = identityProviderPort.getUserInfo(accessToken);
        save(user);
        return jwtServicePort.generate(user.getEmail());
    }

    public Optional<User> findByEmail(String email) {
        return userRepositoryPort.findByEmail(email);
    }
}
