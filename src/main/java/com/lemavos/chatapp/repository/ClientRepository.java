package com.lemavos.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<user, long> {
    
}
