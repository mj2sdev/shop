package io.mj2sdev.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.mj2sdev.shop.model.entity.ProductEntity;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long> {

	public List<ProductEntity> findTop5ByOrderByCreatedAtDesc();

	public List<ProductEntity> findAllByCategory(Integer Category);
	
}
