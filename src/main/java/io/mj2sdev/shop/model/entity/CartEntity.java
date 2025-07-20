package io.mj2sdev.shop.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "cart")
public class CartEntity extends BaseEntity {
	private Integer amount; 
}
