package io.mj2sdev.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.mj2sdev.shop.model.dto.AccountDTO;
import io.mj2sdev.shop.model.dto.CartDTO;
import io.mj2sdev.shop.model.entity.CartEntity;
import io.mj2sdev.shop.model.entity.CartItemEntity;
import io.mj2sdev.shop.repository.AccountRepo;
import io.mj2sdev.shop.repository.CartItemRepo;
import io.mj2sdev.shop.repository.CartRepo;
import io.mj2sdev.shop.repository.ProductRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartManager implements CartService {

	private final AccountRepo accountRepo;

	private final ProductRepo productRepo;

	private final CartRepo cartRepo;

	private final CartItemRepo cartItemRepo;
	
	@Transactional
	public boolean addProductInCart(AccountDTO accountDto, CartDTO cartDto) {
		var account = accountRepo.findByUsername(accountDto.getUsername()).get();
		var cart = account.getCart();
		if (cart == null) {
			cart = CartEntity.builder()
				.account(account)
				.build();
			cartRepo.save(cart);
			cart = account.getCart();
		}
		
		var product = productRepo.getReferenceById(cartDto.getProductId());
		var itemAlready = cartItemRepo.findByCartAndProduct(cart, product);
		
		if (itemAlready.isPresent()) {
			var item = itemAlready.get();
			item.mergeQuantity(cartDto.getQuantity());
		} else {
			var item = CartItemEntity.builder()
			.product(product)
			.cart(cart)
			.quantity(cartDto.getQuantity())
			.build();
			
			cartItemRepo.save(item);
		}
		
		return true;
	}
}
