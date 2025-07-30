package io.mj2sdev.shop.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Table(name = "cartItem")
@SuperBuilder
@NoArgsConstructor
public class CartItemEntity extends BaseEntity {
	
	@ManyToOne(fetch = FetchType.LAZY)
	private CartEntity cart;

	@OneToOne(fetch = FetchType.LAZY)
	private ProductEntity product;
	
	private Integer quantity;

	public CartItemEntity mergeQuantity(Integer quantity) { 
		this.quantity += quantity;
		return this;
	}
}
