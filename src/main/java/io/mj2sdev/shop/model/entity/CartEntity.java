package io.mj2sdev.shop.model.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Table(name = "cart")
@SuperBuilder
@NoArgsConstructor
public class CartEntity extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "account_id")
	private AccountEntity account;
	
	@OneToMany(mappedBy = "cart")
	@Builder.Default
	private List<CartItemEntity> cartItems = new ArrayList<>();
}
