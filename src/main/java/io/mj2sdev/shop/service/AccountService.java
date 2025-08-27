package io.mj2sdev.shop.service;

import io.mj2sdev.shop.model.dto.AccountDTO;
import io.mj2sdev.shop.model.dto.ResponseDTO;

public interface AccountService {
	ResponseDTO updateAccount(AccountDTO loginedAccount, AccountDTO changedAccountInfo);

	AccountDTO findById(AccountDTO account);

	ResponseDTO insertAccount(AccountDTO account);
}
