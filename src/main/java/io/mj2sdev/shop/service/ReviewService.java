package io.mj2sdev.shop.service;

import java.util.List;

import io.mj2sdev.shop.model.dto.ReviewDTO;

public interface ReviewService {

	List<ReviewDTO> findAllByProductId(Long productId);
	
}
