package com.github.rodmotta.bracket_master.adapters.config;

import com.github.rodmotta.bracket_master.core.ports.IdentityProviderPort;
import com.github.rodmotta.bracket_master.core.ports.JwtServicePort;
import com.github.rodmotta.bracket_master.core.ports.TournamentRepositoryPort;
import com.github.rodmotta.bracket_master.core.ports.UserRepositoryPort;
import com.github.rodmotta.bracket_master.core.service.TournamentService;
import com.github.rodmotta.bracket_master.core.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public UserService userService(IdentityProviderPort identityProviderPort, UserRepositoryPort userRepositoryPort, JwtServicePort jwtServicePort) {
        return new UserService(identityProviderPort, userRepositoryPort, jwtServicePort);
    }

    @Bean
    public TournamentService tournamentService(TournamentRepositoryPort tournamentRepositoryPort) {
        return new TournamentService(tournamentRepositoryPort);
    }
}
