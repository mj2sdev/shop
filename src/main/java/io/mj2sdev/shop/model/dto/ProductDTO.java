package io.mj2sdev.shop.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO extends BaseDTO{
	
	private String name;
	private String description;
	private String imageUrl;

	private Integer category;
	private Integer originPrice;
	private Integer salesPrice;
	private Integer discountRate;

	private Boolean active;
	private Boolean bestSeller;
	private MultipartFile image;
}
