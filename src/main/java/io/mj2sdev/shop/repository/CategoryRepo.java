package io.mj2sdev.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.mj2sdev.shop.model.entity.CategoryEntity;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity, Long> {
	
}
