package com.github.rodmotta.bracket_master.adapters.persistence.jpa.repository;

import com.github.rodmotta.bracket_master.adapters.persistence.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJPARepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}
