package com.github.rodmotta.bracket_master.adapters.persistence.jpa.repository;

import com.github.rodmotta.bracket_master.adapters.persistence.jpa.entity.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TournamentJPARepository extends JpaRepository<TournamentEntity, UUID> {
}
