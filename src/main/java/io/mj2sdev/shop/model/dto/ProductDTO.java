package io.mj2sdev.shop.model.dto;

import io.mj2sdev.shop.model.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO extends BaseDTO{
	private String name;
	private String content;
	private String thumbnail;
	private Integer category;
	private Integer originPrice;
	private Integer salesPrice;
}
