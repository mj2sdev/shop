package io.mj2sdev.shop;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.mj2sdev.shop.model.Cart;
import io.mj2sdev.shop.repository.AccountRepo;
import io.mj2sdev.shop.repository.CartRepo;
import io.mj2sdev.shop.repository.ProductRepo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Transactional
@Slf4j
public class CartTests {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	EntityManager em;

	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	CartRepo cartRepo;
	
	@Test
	
	void cartTest() {
		var account = accountRepo.findById(1l).get();

		var product1 = productRepo.findById(1l).get();
		var product2 = productRepo.findById(2l).get();
		var product3 = productRepo.findById(3l).get();

		var cart = new Cart();
		cart.setAccount(account);

		cart.setProduct(product1);
		cartRepo.save(cart);
		cart.setProduct(product2);
		cartRepo.save(cart);
		cart.setProduct(product3);
		cartRepo.save(cart);

		em.flush();
		em.clear();

		List<Cart> carts = accountRepo.findUsercart(account.getId());
	

		log.info(carts.get(0).getAccount().getCarts().toString());
		Assertions.assertEquals(3, carts.size());


		
	}
	
}
