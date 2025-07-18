package io.mj2sdev.shop.model.mapper;

import org.mapstruct.Mapper;

import io.mj2sdev.shop.model.dto.AccountDTO;
import io.mj2sdev.shop.model.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	public AccountDTO toDTO(Account account);

	public Account toEntity(AccountDTO dto);
}
