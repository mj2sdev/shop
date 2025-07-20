package io.mj2sdev.shop.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.mj2sdev.shop.model.dto.ProductDTO;
import io.mj2sdev.shop.model.entity.ProductEntity;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	@Mapping(target = "imageUrl", source = "productImage.image.url")
	ProductDTO toDTO(ProductEntity product);

	@Mapping(target = "reviews", ignore = true)
	@Mapping(target = "productImage", ignore = true)
	@Mapping(target = "deleted", ignore = true)
	ProductEntity toEntity(ProductDTO dto);
}
