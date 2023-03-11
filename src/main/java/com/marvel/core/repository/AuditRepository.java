package com.marvel.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marvel.model.entities.AuditUser;
import com.marvel.model.entities.UserMarvel;

public interface AuditRepository extends JpaRepository<AuditUser, Long> {
	
}
