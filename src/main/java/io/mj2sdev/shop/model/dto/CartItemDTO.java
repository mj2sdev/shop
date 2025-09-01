package io.mj2sdev.shop.model.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO extends BaseDTO {
	
	@Min(value = 1, message = "수량은 1 이상이어야 할 겁니다.")
	private Integer quantity;
	private Long productId;

	private ProductDTO product;
}
