package io.mj2sdev.shop.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "account")
public class AccountEntity extends BaseEntity{

	private String username;

	private String password;

	private String email;
	
	private String phone;

	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
	private CartEntity cart;
}
