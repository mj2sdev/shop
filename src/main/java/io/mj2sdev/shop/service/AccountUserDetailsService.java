package io.mj2sdev.shop.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.mj2sdev.shop.model.Account;
import io.mj2sdev.shop.repository.AccountRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountUserDetailsService implements UserDetailsService {
	
	private final AccountRepo accountRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);

		Account account = accountRepo.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("없는 아이디 입니다."));
			
		return new User(
			account.getUsername(),
			account.getPassword(),
			List.of(new SimpleGrantedAuthority("ROLE_USER"))
		);
	}
}
