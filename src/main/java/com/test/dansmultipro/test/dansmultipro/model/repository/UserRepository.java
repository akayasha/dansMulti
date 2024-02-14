package com.test.dansmultipro.test.dansmultipro.model.repository;

import com.test.dansmultipro.test.dansmultipro.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

//    User findByUsername(String username);
}
