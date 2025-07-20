package io.mj2sdev.shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import io.mj2sdev.shop.model.dto.ProductDTO;
import io.mj2sdev.shop.model.entity.ProductEntity;
import io.mj2sdev.shop.model.mapper.ProductMapper;
import io.mj2sdev.shop.repository.ProductRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
	
	private final ProductRepo productRepo;
	
	private final ProductMapper productMapper;

	@GetMapping
	public String index(Model model) {
		List<ProductEntity> entities = productRepo.findTop5ByOrderByCreatedAtDesc();
		List<ProductDTO> list = entities
			.stream()
			.map(productMapper::toDTO)
			.toList();
		
		model.addAttribute("list", list);
		return "index";
	}
}
