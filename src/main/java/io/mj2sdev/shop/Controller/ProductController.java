package io.mj2sdev.shop.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.mj2sdev.shop.model.dto.AccountDTO;
import io.mj2sdev.shop.model.dto.CartItemDTO;
import io.mj2sdev.shop.model.dto.ProductDTO;
import io.mj2sdev.shop.model.dto.ResponseDTO;
import io.mj2sdev.shop.model.entity.ProductEntity;
import io.mj2sdev.shop.model.mapper.ProductMapper;
import io.mj2sdev.shop.repository.ProductRepo;
import io.mj2sdev.shop.service.CartService;
import io.mj2sdev.shop.service.ProductService;
import io.mj2sdev.shop.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	private final ReviewService reviewService;

	private final CartService cartService;

	@GetMapping("{gender:man|woman}")
	String man(@PathVariable("gender") String gender, Model model) {
		Integer category = gender.equals("man") ? 1 : 2;

		/**
		 * TODO: 카테고리 구현 후 그에맞는 상품 불러오도록 연동해야함.
		 */
		var list = productService.findAll();

		model
			.addAttribute("list", list)
			.addAttribute("type", category);

		return "product";
	}
	
	@GetMapping("detail/{id}")
	String detail(@PathVariable("id") Long productId, Model model) {
		var product = productService.findById(productId);
		var reviews = reviewService.findAllByProductId(productId);

		model.addAttribute("item", product);
		model.addAttribute("reviews", reviews);
		return "detail";
	}
	
	@GetMapping("cart")
	void cart(Model model, @AuthenticationPrincipal AccountDTO account) {
		List<CartItemDTO> list = cartService.findAllByAccount(account);
		Integer deliveryPrice = 3000;
		Integer totalPrice = list.stream()
			.map(item -> item.getProduct().getSalesPrice())
			.reduce(0, Integer::sum);
			
		model
			.addAttribute("list", list)
			.addAttribute("deliveryPrice", deliveryPrice)
			.addAttribute("totalPrice", totalPrice);
	}

	@PostMapping("cart/add")
	@ResponseBody
	ResponseDTO addCart(
		@AuthenticationPrincipal AccountDTO account, 
		@Valid @RequestBody CartItemDTO cartItem) {
		cartService.addProductInCart(account, cartItem);

		return ResponseDTO.of("매우 성공적인 장바구니 상품슛 이었습니다.");
	}

	@PostMapping("cart/delete/{id}")
	@ResponseBody
	ResponseDTO deleteProductInCart(@PathVariable("id") Long itemId) {
		cartService.deleteItemInCart(itemId);

		return ResponseDTO.of("매우 성공적인 상품 폐기 입니다.");
	}

	@PostMapping("cart/change")
	@ResponseBody
	ResponseDTO changeItemQuantity(@RequestBody CartItemDTO itemDto) {
		cartService.changeItemQuantity(itemDto);

		return ResponseDTO.of("매우 성공적인 상품갯수 변경이었다.");
	}
}
