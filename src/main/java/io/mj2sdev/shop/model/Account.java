package io.mj2sdev.shop.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account extends Common implements UserDetails {

	private String username;
	private String password;
	private String email;
	private String phone;

	@OneToMany(mappedBy = "account")
	private List<Cart> carts = new ArrayList<>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
	}
}
