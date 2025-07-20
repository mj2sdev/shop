package io.mj2sdev.shop.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "productImage")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImageEntity extends BaseEntity {
	
	@OneToOne
	private ProductEntity product;

	@OneToOne(cascade = CascadeType.ALL)
	private FileEntity image;
}