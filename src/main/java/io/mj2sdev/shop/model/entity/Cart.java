package io.mj2sdev.shop.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Cart extends Base {
	// private Account account;
	// private Product product;
	private Integer amount; 
}
