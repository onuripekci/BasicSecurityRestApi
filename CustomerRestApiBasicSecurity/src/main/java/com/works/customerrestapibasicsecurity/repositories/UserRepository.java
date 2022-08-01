package com.works.customerrestapibasicsecurity.repositories;

import com.works.customerrestapibasicsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailEqualsIgnoreCase(String email);
}