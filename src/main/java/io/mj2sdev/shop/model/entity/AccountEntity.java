package io.mj2sdev.shop.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Table(name = "account")
@SuperBuilder
@NoArgsConstructor
public class AccountEntity extends BaseEntity{

	private String username;

	private String password;

	private String email;
	
	private String phone;

	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
	private CartEntity cart;

	public AccountEntity changePassword(String encodedPassword) {
		this.password = encodedPassword;
		return this;
	}

	public AccountEntity changeUsername(String username) {
		this.username = username;
		return this;
	}

	public AccountEntity changePhone(String phone) {
		this.phone = phone;
		return this;
	}
}
