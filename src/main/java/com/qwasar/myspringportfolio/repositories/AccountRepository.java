package com.qwasar.myspringportfolio.repositories;

import com.qwasar.myspringportfolio.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findOneByEmailIgnoreCase(String email);
}
