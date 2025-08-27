package io.mj2sdev.shop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.mj2sdev.shop.model.dto.CartItemDTO;
import io.mj2sdev.shop.model.entity.CartItemEntity;

@Mapper(componentModel = "spring", uses = { ProductMapper.class })
public interface CartItemMapper {
	
	@Mapping(source = "product.id", target = "productId")
	CartItemDTO toDTO(CartItemEntity entity);

	@Mapping(source = "productId", target = "product.id")
	CartItemEntity toEntity(CartItemDTO dto);
}