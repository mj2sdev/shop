package io.mj2sdev.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.mj2sdev.shop.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

	public List<Product> findTop5ByOrderByCreateAtDesc();

	public List<Product> findAllByCategory(Integer Category);
	
}
