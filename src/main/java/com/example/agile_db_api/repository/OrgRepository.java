package com.example.agile_db_api.repository;

import com.example.agile_db_api.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrgRepository extends JpaRepository<Organization, UUID> {
    Optional<Organization> findByEmail(String email);
}
