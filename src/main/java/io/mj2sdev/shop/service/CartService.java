package io.mj2sdev.shop.service;

import io.mj2sdev.shop.model.dto.AccountDTO;
import io.mj2sdev.shop.model.dto.CartDTO;

public interface CartService {
	
	boolean addProductInCart(AccountDTO account, CartDTO dto);
}
