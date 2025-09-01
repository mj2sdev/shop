package io.mj2sdev.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.mj2sdev.shop.exception.AccountNotFoundException;
import io.mj2sdev.shop.model.dto.AccountDTO;
import io.mj2sdev.shop.model.dto.CartItemDTO;
import io.mj2sdev.shop.model.entity.CartEntity;
import io.mj2sdev.shop.model.entity.CartItemEntity;
import io.mj2sdev.shop.model.mapper.CartMapper;
import io.mj2sdev.shop.repository.AccountRepo;
import io.mj2sdev.shop.repository.CartItemRepo;
import io.mj2sdev.shop.repository.CartRepo;
import io.mj2sdev.shop.repository.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartManager implements CartService {
	
	private final AccountRepo accountRepo;

	private final ProductRepo productRepo;

	private final CartRepo cartRepo;

	private final CartMapper cartMapper;

	private final CartItemRepo cartItemRepo;
	
	@Transactional
	public void addProductInCart(AccountDTO accountDto, CartItemDTO cartItemDto) {
		/**
		 * psudo code:
		 * 1. 일단 사용자 정보로 카트 가져오고
		 * - 카트가 없을경우 하나 생성해서 가져옴
		 * 
		 * 2. 카트 리스트에 아이템을 추가한다.
		 * - 아이템이 없을경우 그냥추가
		 * - 이미 있으면 수량만 늘려야함
		 */
		var account = accountRepo.findByUsername(accountDto.getUsername())
			.orElseThrow(() -> new AccountNotFoundException(accountDto.getUsername()));
		
		var cart = Optional.ofNullable(account.getCart())
			.orElseGet(() -> cartRepo.save(CartEntity.builder()
				.account(account)
				.build()));

		var product = productRepo.findById(cartItemDto.getProductId())
			.orElseThrow(() -> new EntityNotFoundException("[%d] 아이디의 상품을 찾을 수 없습니다.".formatted(cartItemDto.getProductId())));

		cartItemRepo.findByCartAndProduct(cart, product)
			.ifPresentOrElse(
				item -> item.mergeQuantity(cartItemDto.getQuantity()),
				() -> {
					cartItemRepo.save(CartItemEntity.builder()
						.cart(cart)
						.product(product)
						.quantity(cartItemDto.getQuantity())
						.build());
				});
	}

	@Override
	public List<CartItemDTO> findAllByAccount(AccountDTO accountDto) {
		var account = accountRepo.findByUsername(accountDto.getUsername())
			.orElseThrow(() -> new AccountNotFoundException(accountDto.getUsername()));
		
		var cart = Optional.ofNullable(account.getCart())
			.orElseGet(() -> {
				return cartRepo.save(CartEntity.builder()
					.account(account)
					.build());
			});

		return cartMapper.toDTO(cart).getCartItems();
	}

	@Override
	public void deleteItemInCart(Long itemId) {
		cartItemRepo.deleteById(itemId);
	}

	@Override
	@Transactional
	public void changeItemQuantity(CartItemDTO cartItemDto) {
		CartItemEntity entity = cartItemRepo.findById(cartItemDto.getId()).get();
		entity.setQuantity(cartItemDto.getQuantity());
	}
}
