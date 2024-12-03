package com.yc.SecurePro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yc.SecurePro.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
