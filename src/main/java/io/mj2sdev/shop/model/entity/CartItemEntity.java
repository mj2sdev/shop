package io.mj2sdev.shop.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Table(name = "cartItem")
@SuperBuilder
@NoArgsConstructor
public class CartItemEntity extends BaseEntity {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Setter
	private CartEntity cart;

	@OneToOne(fetch = FetchType.LAZY)
	private ProductEntity product;
	
	@Setter
	private Integer quantity;

	public CartItemEntity mergeQuantity(Integer quantity) { 
		this.quantity += quantity;
		return this;
	}
}
