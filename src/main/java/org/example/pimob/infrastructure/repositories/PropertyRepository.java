package org.example.pimob.infrastructure.repositories;

import org.example.pimob.domain.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    boolean existsByEnderecoAndNumeroAndCep(String endereco, String numero, String cep);
    long countByCriadoPor(Long criadoPor);
}
