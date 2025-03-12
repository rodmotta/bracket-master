package com.github.rodmotta.bracket_master.adapters.persistence.repository;

import com.github.rodmotta.bracket_master.adapters.persistence.jpa.entity.TournamentEntity;
import com.github.rodmotta.bracket_master.adapters.persistence.jpa.repository.TournamentJPARepository;
import com.github.rodmotta.bracket_master.core.model.Tournament;
import com.github.rodmotta.bracket_master.core.ports.TournamentRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TournamentRepository implements TournamentRepositoryPort {
    private final TournamentJPARepository jpaRepository;

    public TournamentRepository(TournamentJPARepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Tournament> findAll() {
        return jpaRepository.findAll().stream()
                .map(TournamentEntity::toModel)
                .toList();
    }

    @Override
    public Optional<Tournament> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(TournamentEntity::toModel);
    }

    @Override
    public void save(Tournament tournament) {
        TournamentEntity tournamentEntity = new TournamentEntity(tournament);
        jpaRepository.save(tournamentEntity);
    }

    @Override
    public void delete(Tournament tournament) {
        TournamentEntity tournamentEntity = new TournamentEntity(tournament);
        jpaRepository.delete(tournamentEntity);
    }
}
