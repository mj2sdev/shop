package io.mj2sdev.shop.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.mj2sdev.shop.model.entity.AccountEntity;
import io.mj2sdev.shop.model.mapper.AccountMapper;
import io.mj2sdev.shop.repository.AccountRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountUserDetailsService implements UserDetailsService {
	
	private final AccountRepo accountRepo;

	private final AccountMapper accountMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountEntity account = accountRepo.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("없는 아이디 입니다."));
			
		return accountMapper.toDTO(account);
	}
}
