package org.example.pimob.infrastructure.repositories;

import org.example.pimob.domain.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<TeamEntity, Long> {
  Optional<TeamEntity> findByName(String name);
}
