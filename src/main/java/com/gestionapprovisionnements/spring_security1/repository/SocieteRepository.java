package com.gestionapprovisionnements.spring_security1.repository;

import com.gestionapprovisionnements.spring_security1.entity.Societe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocieteRepository extends JpaRepository<Societe, Long> {
    boolean existsByIce(String ice);
}
