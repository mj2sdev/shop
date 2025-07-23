package io.mj2sdev.shop.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.mj2sdev.shop.model.dto.ProductDTO;

public interface ProductService {
	
	List<ProductDTO> findAll();

	boolean save(ProductDTO dto, MultipartFile image);

	boolean update(ProductDTO dto);

	boolean delete(Long id);

	ProductDTO findById(Long id);

	boolean deleteAllbyIds(List<Long> ids);
}
