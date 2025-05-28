package org.example.pimob.infrastructure.repositories;

import org.example.pimob.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<Usuario, Long> {
  Optional<Usuario> findByEmail(String email);

  @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.corretores WHERE u.email = :email")
  Optional<Usuario> findByEmailWithCorretores(@Param("email") String email);

}
