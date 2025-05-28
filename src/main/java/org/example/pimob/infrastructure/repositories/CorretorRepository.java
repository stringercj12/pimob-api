package org.example.pimob.infrastructure.repositories;

import java.util.Optional;

import org.example.pimob.domain.entities.Corretor;
import org.example.pimob.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorretorRepository extends JpaRepository<Corretor, Long> {
  Optional<Corretor> findByUserId(Long usuarioId);
}
