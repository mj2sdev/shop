package io.mj2sdev.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.mj2sdev.shop.model.entity.AccountEntity;

@Repository
public interface AccountRepo extends JpaRepository<AccountEntity, Long>{

	Optional<AccountEntity> findByUsername(String username);

	// @Query("select c from Cart c join fetch c.account A join fetch c.product p where A.id = :accountId")
	// List<Cart> findUsercart(@Param("accountId") Long accountId);
	
}
