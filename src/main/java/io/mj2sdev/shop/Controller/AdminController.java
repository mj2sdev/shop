package io.mj2sdev.shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.mj2sdev.shop.model.dto.ProductDTO;
import io.mj2sdev.shop.model.entity.Product;
import io.mj2sdev.shop.repository.ProductRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

	private final ProductRepo productRepo;
	
	@GetMapping
	String admin(Model model) {
		// List<Product> entities = productRepo.findAll();
		
		// model.addAttribute("list", dtos);

		return "admin";
	}

	@GetMapping("product/add")
	String addProduct(Model model) {
		model.addAttribute("product", new ProductDTO());
		return "addProduct";
	}

	@PostMapping("product/add")
	public String addProduct(@ModelAttribute ProductDTO product, RedirectAttributes attributes) {
		// Product entity = product.toEntity();
		// Product saved = productRepo.save(entity);

		// attributes.addAttribute("result", saved != null);

		return "redirect:/admin";
	}
	
}
