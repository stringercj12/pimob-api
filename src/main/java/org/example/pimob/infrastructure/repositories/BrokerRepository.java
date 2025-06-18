package org.example.pimob.infrastructure.repositories;

import java.util.Optional;

import org.example.pimob.domain.entities.Broker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokerRepository extends JpaRepository<Broker, Long> {
  Optional<Broker> findByUserId(Long usuarioId);
}

