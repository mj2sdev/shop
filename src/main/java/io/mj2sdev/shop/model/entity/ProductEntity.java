package io.mj2sdev.shop.model.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Table(name = "product")
@SuperBuilder
@NoArgsConstructor
@SQLRestriction("deleted = false")
public class ProductEntity extends BaseEntity {

	@Lob
	private String description;
	private String name;

	private Integer category;
	private Integer originPrice;
	private Integer salesPrice;
	private Integer discountRate;
	
	private Boolean active;
	private Boolean bestSeller;

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	private ProductImageEntity productImage;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ReviewEntity> reviews = new ArrayList<>();
}
