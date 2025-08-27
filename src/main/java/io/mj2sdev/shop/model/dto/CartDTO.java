package io.mj2sdev.shop.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO extends BaseDTO {
	
	private Long accountId;
	private List<CartItemDTO> cartItems;

}
