package com.lemavos.chatapp.repository;

import java.util.Optional;

import com.lemavos.chatapp.entity.User;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}
