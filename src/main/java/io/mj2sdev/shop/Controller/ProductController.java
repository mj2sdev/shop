package io.mj2sdev.shop.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.mj2sdev.shop.model.dto.AccountDTO;
import io.mj2sdev.shop.model.dto.ProductDTO;
import io.mj2sdev.shop.model.entity.AccountEntity;
import io.mj2sdev.shop.model.entity.CartEntity;
import io.mj2sdev.shop.model.entity.ProductEntity;
import io.mj2sdev.shop.model.mapper.ProductMapper;
import io.mj2sdev.shop.repository.AccountRepo;
import io.mj2sdev.shop.repository.CartRepo;
import io.mj2sdev.shop.repository.ProductRepo;
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

	private final AccountRepo accountRepo;

	private final CartRepo cartRepo;

	@GetMapping("{gender:man|woman}")
	String man(@PathVariable("gender") String gender, Model model) {
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
	
	@GetMapping("detail/{productId}")
	public String detail(@PathVariable("productId") Long productId, Model model) {
		ProductEntity entity = productRepo.findById(productId).get();
		var item = productMapper.toDTO(entity);

		model.addAttribute("item", productMapper.toDTO(entity));
		model.addAttribute("reviews", entity.getReviews());
		return "detail";
	}
	
	@GetMapping("cart")
	String cart(Model model, @AuthenticationPrincipal AccountDTO account) {
		System.out.println(account.getUsername());
		return "cart";
	}

	@PostMapping("cart/add")
	@ResponseBody
	Boolean addCart(@AuthenticationPrincipal AccountEntity account,@RequestBody CartEntity cart) {
		cartRepo.save(cart);
		return true;
	}
}
