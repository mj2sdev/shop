package io.mj2sdev.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.mj2sdev.shop.model.entity.CartEntity;
import io.mj2sdev.shop.model.entity.CartItemEntity;
import io.mj2sdev.shop.model.entity.ProductEntity;

@Repository
public interface CartItemRepo extends JpaRepository<CartItemEntity, Long> {

	Optional<CartItemEntity> findByCartAndProduct(CartEntity cart, ProductEntity product);
}
