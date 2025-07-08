package io.mj2sdev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.mj2sdev.shop.model.Account;
import jakarta.persistence.Table;

@Repository
@Table(name = "member")
public interface AccountRepo extends JpaRepository<Account, String>{
	
}
