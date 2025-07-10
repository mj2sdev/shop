package io.mj2sdev.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends Common {
	@Lob
	private String content;
	private String name;
	private String thumbnail;
	private Integer category;
	private Integer originPrice;
	private Integer salesPrice;
	private Boolean isCarrot;
	private Boolean isNugu;
}
