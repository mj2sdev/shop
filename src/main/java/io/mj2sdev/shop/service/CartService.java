package io.mj2sdev.shop.service;

import java.util.List;

import io.mj2sdev.shop.model.dto.AccountDTO;
import io.mj2sdev.shop.model.dto.CartItemDTO;

public interface CartService {
	
	void addProductInCart(AccountDTO account, CartItemDTO dto);

	List<CartItemDTO> findAllByAccount(AccountDTO account);
}
