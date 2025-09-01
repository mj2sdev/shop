package io.mj2sdev.shop.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.mj2sdev.shop.exception.AccountNotFoundException;
import io.mj2sdev.shop.model.dto.AccountDTO;
import io.mj2sdev.shop.model.dto.ResponseDTO;
import io.mj2sdev.shop.model.entity.AccountEntity;
import io.mj2sdev.shop.model.mapper.AccountMapper;
import io.mj2sdev.shop.repository.AccountRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountManager implements AccountService {

	private final AccountMapper accountMapper;

	private final AccountRepo accountRepo;

	private final PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public ResponseDTO updateAccount(AccountDTO loginedAccount, AccountDTO changedAccountInfo) {
		try {

			AccountEntity entity = accountRepo.findById(loginedAccount.getId()).get();
			
			if (!changedAccountInfo.getPassword().isEmpty()) {
				String encodedPassword = passwordEncoder.encode(changedAccountInfo.getPassword());
				entity.changePassword(encodedPassword);
			}
			
			entity.changePhone(changedAccountInfo.getPhone());
			entity.changeUsername(changedAccountInfo.getUsername());

			return ResponseDTO.builder()
				.message("회원정보 수정이 완료됨")
				.result(true)
				.build();
		} catch (Exception e) {
			e.printStackTrace();
			String message = "회원정보 수정 중 오류 발생";
			System.out.println(message);
			return ResponseDTO.builder()
				.message(message)
				.result(false)
				.build();
		}
	}

	@Override
	public AccountDTO findById(AccountDTO account) {
		AccountEntity entity = accountRepo.findById(account.getId()).orElseThrow(() -> new EntityNotFoundException("객체를 못찾음"));
		return accountMapper.toDTO(entity);
	}

	@Override
	public ResponseDTO insertAccount(AccountDTO account) {
		String message = "계정이 생성되었습니다.";
		Boolean result = true;
		try {

			String rawPassword = account.getPassword();
			String cryptedPassword = passwordEncoder.encode(rawPassword);
			account.setPassword(cryptedPassword);
			
			AccountEntity entity = accountMapper.toEntity(account);
			result = accountRepo.save(entity) != null;

		} catch (Exception e) {
			e.printStackTrace();
			message = "계정생성 중 오류가 발생하였습니다.";
		}
		
		return ResponseDTO.builder()
			.message(message)
			.result(result)
			.build();
	}

	@Override
	public AccountEntity findAccountByPrincipal(AccountDTO accountDto) {
		return accountRepo.findByUsername(accountDto.getUsername())
			.orElseThrow(() -> new AccountNotFoundException(accountDto.getUsername()));
	}
	
}
