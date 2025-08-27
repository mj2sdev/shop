package io.mj2sdev.shop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.mj2sdev.shop.model.dto.CartDTO;
import io.mj2sdev.shop.model.entity.CartEntity;

@Mapper(componentModel = "spring", uses = { CartItemMapper.class })
public interface CartMapper {
	
	@Mapping(source = "account.id", target = "accountId")
	CartDTO toDTO(CartEntity entity);

	@Mapping(target = "account", ignore = true)
	CartEntity toEntity(CartDTO dto);
}
