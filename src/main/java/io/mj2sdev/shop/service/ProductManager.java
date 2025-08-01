package io.mj2sdev.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import io.mj2sdev.shop.model.dto.ProductDTO;
import io.mj2sdev.shop.model.entity.FileEntity;
import io.mj2sdev.shop.model.entity.ProductEntity;
import io.mj2sdev.shop.model.entity.ProductImageEntity;
import io.mj2sdev.shop.model.mapper.ProductMapper;
import io.mj2sdev.shop.repository.ProductFileRepo;
import io.mj2sdev.shop.repository.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {

	private final ProductRepo productRepo;

	private final ProductMapper productMapper;

	private final ProductFileRepo productFileRepo;

	private final FileService fileService;

	@Override
	public List<ProductDTO> findAll() {
		return productRepo.findAll()
			.stream()
			.map(productMapper::toDTO)
			.toList();
	}

	@Override
	public boolean save(ProductDTO dto, MultipartFile image) {

		ProductEntity productEntity = productMapper.toEntity(dto);
		ProductEntity savedProduct = productRepo.save(productEntity);
		FileEntity fileEntity = fileService.save(image);

		ProductImageEntity productImageEntity = ProductImageEntity.builder()
			.product(savedProduct)
			.image(fileEntity)
			.build();
		
		productFileRepo.save(productImageEntity);
		return true;
	}

	@Override
	@Transactional
	public boolean update(ProductDTO dto) {
		ProductEntity product = productRepo.getReferenceById(dto.getId());
		ProductImageEntity productImageEntity = product.getProductImage();

		if (!dto.getImage().isEmpty()) {
			FileEntity fileEntity = fileService.save(dto.getImage());
			productImageEntity.setImage(fileEntity);
		}
		product.update(dto);

		return true;
	}

	@Override
	public ProductDTO findById(Long id) {
		var entity = productRepo.findById(id).get();
		return productMapper.toDTO(entity);
	}

	@Override
	@Transactional
	public boolean deleteAllbyIds(List<Long> ids) {
		ids.forEach(this::delete);
		return true;
	}

	@Override
	public boolean delete(Long id) {
		ProductEntity entity = productRepo.getReferenceById(id);
		entity.delete();
		return true;
	}
	
}
