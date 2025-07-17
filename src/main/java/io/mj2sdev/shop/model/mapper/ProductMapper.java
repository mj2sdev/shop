package io.mj2sdev.shop.model.mapper;

import org.mapstruct.Mapper;

import io.mj2sdev.shop.model.dto.ProductDTO;
import io.mj2sdev.shop.model.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	ProductDTO toDTO(Product product);

	Product toEntity(ProductDTO dto);
}
