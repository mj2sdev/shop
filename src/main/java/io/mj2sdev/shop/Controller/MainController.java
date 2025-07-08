package io.mj2sdev.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.mj2sdev.shop.repository.ProductRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
	
	private final ProductRepo productRepo;

	@GetMapping
	public String index(Model model) {
		var list = productRepo.findTop5ByOrderByIndateDesc();
		model.addAttribute("list", list);
		return "index";
	}
	
}
