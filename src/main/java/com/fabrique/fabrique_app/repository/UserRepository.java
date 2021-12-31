package com.fabrique.fabrique_app.repository;

import com.fabrique.fabrique_app.model.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    @Override
    Optional<AppUser> findById(Long id);

    Optional<AppUser> findByLogin(String login);
}
