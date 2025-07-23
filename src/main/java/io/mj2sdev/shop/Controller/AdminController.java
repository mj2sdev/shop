package io.mj2sdev.shop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.mj2sdev.shop.model.dto.ProductDTO;
import io.mj2sdev.shop.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

	private final ProductService productService;
	
	@GetMapping
	String admin(Model model) {
		var list = productService.findAll();
		model.addAttribute("list", list);

		return "admin";
	}

	@GetMapping("product/add")
	String addProduct(Model model) {
		model.addAttribute("product", new ProductDTO());
		return "admin/product/create";
	}

	@PostMapping("product/add")
	public String addProduct(
		@RequestParam("image") MultipartFile image,
		@ModelAttribute ProductDTO product, 
		RedirectAttributes attributes) {

		boolean result = productService.save(product, image);
		attributes.addAttribute("result", true);

		return "redirect:/admin";
	}

	@PostMapping("product/delete")
	public String postMethodName(@RequestParam("ids") List<Long> ids) {
		boolean result = productService.deleteAllbyIds(ids);
		return "redirect:/admin";
	}
	
	
}
