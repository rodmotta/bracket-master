package com.github.rodmotta.bracket_master.adapters.persistence.repository;

import com.github.rodmotta.bracket_master.adapters.persistence.jpa.entity.UserEntity;
import com.github.rodmotta.bracket_master.adapters.persistence.jpa.repository.UserJPARepository;
import com.github.rodmotta.bracket_master.core.model.User;
import com.github.rodmotta.bracket_master.core.ports.UserRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository implements UserRepositoryPort {
    private final UserJPARepository jpaRepository;

    public UserRepository(UserJPARepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(UserEntity::toDomain);
    }

    @Override
    public void save(User user) {
        var userEntity = new UserEntity(user);
        jpaRepository.save(userEntity);
    }
}
