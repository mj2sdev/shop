package io.mj2sdev.shop.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class Cart extends Base {
	
	@ManyToOne
	@JoinColumn(name = "accountId")
	private Account account;

	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
	private Integer amount; 
}
