package io.mj2sdev.shop.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Product extends Base {

	@Lob
	private String content;
	private String name;
	private String thumbnail;
	private Integer category;
	private Integer originPrice;
	private Integer salesPrice;
	private Boolean isCarrot;
	private Boolean isNugu;

	@Builder.Default
	@OneToMany(mappedBy = "product")
	private List<Review> reviews = new ArrayList<>();
}
