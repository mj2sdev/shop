package io.mj2sdev.shop.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class Product extends Base {

	@Lob
	private String content;
	private String name;
	private String thumbnail;

	private Integer category;
	private Integer originPrice;
	private Integer salesPrice;
	
	private Boolean isActive;
	private Boolean isBestSeller;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Review> reviews = new ArrayList<>();
}
