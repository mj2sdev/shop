package io.mj2sdev.shop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.mj2sdev.shop.model.dto.AccountDTO;
import io.mj2sdev.shop.model.entity.AccountEntity;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	
	@Mapping(target = "authorities", ignore = true)
	AccountDTO toDTO(AccountEntity account);

	AccountEntity toEntity(AccountDTO dto);
}
