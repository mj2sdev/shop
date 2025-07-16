package io.mj2sdev.shop;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import io.mj2sdev.shop.model.entity.Cart;
import io.mj2sdev.shop.model.entity.Review;
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
}
