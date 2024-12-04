package com.yc.SecurePro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yc.SecurePro.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUserName(String userName);
}
