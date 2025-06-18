package org.example.pimob.infrastructure.repositories;

import org.example.pimob.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

  @Query("SELECT u FROM User u LEFT JOIN FETCH u.corretor WHERE u.email = :email")
  Optional<User> findByEmailWithCorretores(@Param("email") String email);

}
