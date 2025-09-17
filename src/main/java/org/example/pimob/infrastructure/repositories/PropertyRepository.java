package org.example.pimob.infrastructure.repositories;

import org.example.pimob.domain.entities.Property;
import org.example.pimob.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    boolean existsByLogradouroAndNumeroAndComplementoAndCep(String logradouro, String numero,String complemento, String cep);
    List<Property> findByCriadoPor(User user);
}


