package io.mj2sdev.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.mj2sdev.shop.model.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long>{

	Optional<Account> findByUsername(String username);
	
}
