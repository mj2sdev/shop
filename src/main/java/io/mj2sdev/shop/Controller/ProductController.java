package io.mj2sdev.shop.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.mj2sdev.shop.model.dto.ProductDTO;
import io.mj2sdev.shop.model.entity.Account;
import io.mj2sdev.shop.model.entity.Cart;
import io.mj2sdev.shop.model.entity.Product;
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

		List<Product> entities = productRepo.findAllByCategory(category);
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
		Product entity = productRepo.findById(productId).get();

		model.addAttribute("item", productMapper.toDTO(entity));
		model.addAttribute("reviews", entity.getReviews());
		return "detail";
	}
	
	@GetMapping("cart")
	String cart(Model model) {
		Account account = accountRepo.findById(1l).get();
		// var list = accountRepo.findUsercart(account.getId());
		// var carts = account.getCarts();
		// model
		// 	.addAttribute("list", list);
			// .addAttribute("carts", carts);
		return "cart";
	}

	@PostMapping("cart/add")
	@ResponseBody
	Boolean addCart(@AuthenticationPrincipal Account account,@RequestBody Cart cart) {
		cartRepo.save(cart);
		return true;
	}
}
