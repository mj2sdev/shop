package io.mj2sdev.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.mj2sdev.shop.model.dto.ProductDTO;
import io.mj2sdev.shop.model.entity.Product;
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
		List<Product> entities = productRepo.findTop5ByOrderByCreatedAtDesc();
		List<ProductDTO> list = entities
			.stream()
			.map(productMapper::toDTO)
			.toList();
		
		model.addAttribute("list", list);
		return "index";
	}
}
