package com.gestionapprovisionnements.spring_security1.repository;

import com.gestionapprovisionnements.spring_security1.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository  extends JpaRepository<Document, Long> {
}
