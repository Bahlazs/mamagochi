package com.codecool.grannymanager.repository;

import com.codecool.grannymanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    User findUserByEmail(String email);

    Optional<User> findUserByUserName(String username);

    boolean existsByUserName(String userName);

}

