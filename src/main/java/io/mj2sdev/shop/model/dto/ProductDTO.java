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

	public ProductDTO(Product product) {
		this.Copy(product);
		this.name    = product.getName();
		this.content  = product.getContent();
		this.thumbnail = product.getThumbnail();
		this.category   = product.getCategory();
		this.originPrice = product.getOriginPrice();
		this.salesPrice   = product.getSalesPrice();
	}

	public Product toEntity() {
		return Product.builder()
			.name(this.getName())
			.content(this.getContent())
			.thumbnail(this.getThumbnail())
			.category(this.getCategory())
			.originPrice(this.getOriginPrice())
			.salesPrice(this.getSalesPrice())
			.build();
	}
}
