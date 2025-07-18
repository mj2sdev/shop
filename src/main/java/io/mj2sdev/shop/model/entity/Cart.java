package io.mj2sdev.shop.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Cart extends Base {
	private Integer amount; 
}
