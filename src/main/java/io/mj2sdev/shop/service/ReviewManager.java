package io.mj2sdev.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.mj2sdev.shop.model.dto.ReviewDTO;
import io.mj2sdev.shop.model.mapper.ReviewMapper;
import io.mj2sdev.shop.repository.ProductRepo;
import io.mj2sdev.shop.repository.ReviewRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewManager implements ReviewService {

	private final ReviewMapper reviewMapper;

	private final ProductRepo productRepo;

	@Override
	public List<ReviewDTO> findAllByProductId(Long productId) {
		var product = productRepo.getReferenceById(productId);
		return product.getReviews()
			.stream()
			.map(reviewMapper::toDTO)
			.toList();
	}
	
}
