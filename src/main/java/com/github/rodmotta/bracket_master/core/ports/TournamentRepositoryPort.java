package com.github.rodmotta.bracket_master.core.ports;

import com.github.rodmotta.bracket_master.core.model.Tournament;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TournamentRepositoryPort {
    List<Tournament> findAll();
    Optional<Tournament> findById(UUID id);
    void save(Tournament tournament);
    void delete(Tournament tournament);
}
