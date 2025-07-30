package io.mj2sdev.shop.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO extends BaseDTO {
	
	private Long productId;
	
	private Integer quantity;
}
