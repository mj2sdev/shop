package io.mj2sdev.shop.service;

import io.mj2sdev.shop.model.dto.AccountDTO;
import io.mj2sdev.shop.model.dto.ResponseDTO;
import io.mj2sdev.shop.model.entity.AccountEntity;

public interface AccountService {
	AccountEntity findAccountByPrincipal(AccountDTO accountDto);

	ResponseDTO updateAccount(AccountDTO loginedAccount, AccountDTO changedAccountInfo);

	AccountDTO findById(AccountDTO account);

	ResponseDTO insertAccount(AccountDTO account);
}
