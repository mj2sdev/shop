package io.mj2sdev.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.mj2sdev.shop.repository.ProductRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductRepo productRepo;

	@GetMapping("{gender:man|woman}")
	String man(@PathVariable("gender") String gender, Model model) {
		Integer category = gender.equals("man") ? 1 : 2;
		var list = productRepo.findAllByCategory(category);
		model
			.addAttribute("list", list)
			.addAttribute("type", category);

		return "product";
	}
	
	@GetMapping("detail/{pseq}")
	public String detail(@PathVariable("pseq") Integer pseq, Model model) {
		var item = productRepo.findById(pseq);
		model.addAttribute("item", item.get());
		return "detail";
	}
	
}
