package io.mj2sdev.shop.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.mj2sdev.shop.model.dto.AccountDTO;
import io.mj2sdev.shop.model.dto.CartDTO;
import io.mj2sdev.shop.model.dto.ProductDTO;
import io.mj2sdev.shop.model.entity.ProductEntity;
import io.mj2sdev.shop.model.mapper.ProductMapper;
import io.mj2sdev.shop.repository.ProductRepo;
import io.mj2sdev.shop.service.CartService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductRepo productRepo;
	
	private final ProductMapper productMapper;

	private final CartService cartService;

	@GetMapping("{gender:man|woman}")
	String man(@PathVariable String gender, Model model) {
		Integer category = gender.equals("man") ? 1 : 2;

		List<ProductEntity> entities = productRepo.findAllByCategory(category);
		List<ProductDTO> list = entities
			.stream()
			.map(productMapper::toDTO)
			.toList();

		model
			.addAttribute("list", list)
			.addAttribute("type", category);

		return "product";
	}
	
	@GetMapping("detail/{id}")
	String detail(@PathVariable("id") Long productId, Model model) {
		ProductEntity entity = productRepo.findById(productId).get();
		var item = productMapper.toDTO(entity);

		model.addAttribute("item", productMapper.toDTO(entity));
		model.addAttribute("reviews", entity.getReviews());
		return "detail";
	}
	
	@GetMapping("cart")
	void cart(Model model, @AuthenticationPrincipal AccountDTO account) {
		System.out.println(account.getUsername());
	}

	@PostMapping("cart/add")
	@ResponseBody
	boolean addCart(
		@AuthenticationPrincipal AccountDTO account, 
		@RequestBody CartDTO cartItem) {
		boolean result = cartService.addProductInCart(account, cartItem);

		return result;
	}
}
