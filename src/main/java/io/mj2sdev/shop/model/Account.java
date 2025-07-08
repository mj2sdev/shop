package io.mj2sdev.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member")
public class Account {
	
	@Id
	private String writer;
	private String userid;
	private String password;
	private String email;
	private String phone;
}
