
package com.gestionapprovisionnements.spring_security1.repository;

import com.gestionapprovisionnements.spring_security1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // important for login
}



