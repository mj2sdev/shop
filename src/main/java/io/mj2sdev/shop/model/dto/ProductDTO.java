package io.mj2sdev.shop.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO extends BaseDTO{
	
	private String name;
	private String content;
	private String thumbnail;

	private Integer category;
	private Integer price;
	private Integer offRate;

	private Boolean isActive;
	private Boolean isBestSeller;
}
