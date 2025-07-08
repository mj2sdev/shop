package io.mj2sdev.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.mj2sdev.shop.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

	@Query(nativeQuery = true, value = """
		SELECT * FROM 
		(SELECT * FROM product ORDER BY indate DESC) p
		WHERE ROWNUM < 6
	""")
	public List<Product> findTop5ByOrderByIndateDesc();

	public List<Product> findAllByKind(String kind);
	
}
