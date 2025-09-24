package org.example.pimob.infrastructure.repositories;

import org.example.pimob.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
  Optional<UserEntity> findByEmail(String email);

  @Query("SELECT u FROM UserEntity u JOIN FETCH u.teams where u.email = :email")
  Optional<UserEntity> findByEmailFetchTeams(@Param("email") String email);
}
