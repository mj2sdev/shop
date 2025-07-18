package io.mj2sdev.shop.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Account extends Base{

	private String username;
	private String password;
	private String email;
	private String phone;
}
