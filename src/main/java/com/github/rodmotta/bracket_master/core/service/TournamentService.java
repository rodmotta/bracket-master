package com.github.rodmotta.bracket_master.core.service;

import com.github.rodmotta.bracket_master.core.model.Tournament;
import com.github.rodmotta.bracket_master.core.ports.TournamentRepositoryPort;

import java.util.List;
import java.util.UUID;

public class TournamentService {
    private final TournamentRepositoryPort tournamentRepositoryPort;

    public TournamentService(TournamentRepositoryPort tournamentRepositoryPort) {
        this.tournamentRepositoryPort = tournamentRepositoryPort;
    }

    public List<Tournament> findAll() {
        return tournamentRepositoryPort.findAll();
    }

    public void create(Tournament tournament) {
        tournament.setId(null);
        tournamentRepositoryPort.save(tournament);
    }

    public void updateById(UUID id, Tournament tournament) {
        tournamentRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException()); //fixme

        tournament.setId(id);
        tournamentRepositoryPort.save(tournament);
    }

    public void deleteById(UUID id) {

        Tournament tournament = tournamentRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException()); //fixme

        tournamentRepositoryPort.delete(tournament);
    }

    public Tournament findById(UUID id) {
        return tournamentRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException()); //fixme
    }
}
