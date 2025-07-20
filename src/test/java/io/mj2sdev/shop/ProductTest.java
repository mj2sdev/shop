package io.mj2sdev.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.mj2sdev.shop.model.dto.ProductDTO;
import io.mj2sdev.shop.model.mapper.ProductMapper;
import io.mj2sdev.shop.repository.AccountRepo;
import io.mj2sdev.shop.repository.CartRepo;
import io.mj2sdev.shop.repository.ProductRepo;
import io.mj2sdev.shop.repository.ReviewRepo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
// @Commit 결과 유지시 사용
public class ProductTest {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	EntityManager em;

	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	CartRepo cartRepo;

	@Autowired
	ReviewRepo reviewRepo;

	@Autowired
	ProductMapper productMapper;

	@Test
	void werwerwer() {
		var entity = productRepo.findById(1l).get();
		ProductDTO dto = productMapper.toDTO(entity);

		System.out.println(dto.getName());
		
		Assertions.assertNotNull(entity);
	}
}
