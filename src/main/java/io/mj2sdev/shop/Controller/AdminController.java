package io.mj2sdev.shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.mj2sdev.shop.model.Product;
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
		var list = productRepo.findAll();
		model.addAttribute("list", list);

		return "admin";
	}

	@GetMapping("product/add")
	String addProduct(Model model) {
		model.addAttribute("product", new Product());
		return "addProduct";
	}

	@PostMapping("product/add")
	public String addProduct(@ModelAttribute Product product, RedirectAttributes attributes) {
		Product saved = productRepo.save(product);

		attributes.addAttribute("result", saved != null);

		return "redirect:/admin";
	}
	
}
