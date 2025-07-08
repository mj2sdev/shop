package io.mj2sdev.shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "member")
public class Account {
	
	@Id
	private String writer;

	@Column(name = "userid")
	private String username;
	private String password;
	private String email;
	private String phone;
}
