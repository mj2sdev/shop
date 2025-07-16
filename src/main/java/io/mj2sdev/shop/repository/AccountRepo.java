package io.mj2sdev.shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.mj2sdev.shop.model.entity.Account;
import io.mj2sdev.shop.model.entity.Cart;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long>{

	Optional<Account> findByUsername(String username);

	@Query("select c from Cart c join fetch c.account A join fetch c.product p where A.id = :accountId")
	List<Cart> findUsercart(@Param("accountId") Long accountId);
	
}
