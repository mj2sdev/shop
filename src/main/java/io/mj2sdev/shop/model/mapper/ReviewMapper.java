package io.mj2sdev.shop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.mj2sdev.shop.model.dto.ReviewDTO;
import io.mj2sdev.shop.model.entity.ReviewEntity;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
	
	@Mapping(source = "account.id", target = "accountId")
	@Mapping(source = "product.id", target = "productId")
	public ReviewDTO toDTO(ReviewEntity entity);

	@Mapping(source = "accountId", target = "account.id")
	@Mapping(source = "productId", target = "product.id")
	public ReviewEntity toEntity(ReviewDTO dto);
}
