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

	@GetMapping("man")
	String man(Model model) {
		String kind = "1"; // man
		var list = productRepo.findAllByKind(kind);
		model.addAttribute("list", list)
			.addAttribute("kind", kind);

		return "product";
	}

	@GetMapping("woman")
	String woman(Model model) {
		String kind = "2"; // man
		var list = productRepo.findAllByKind(kind);
		model.addAttribute("list", list)
			.addAttribute("kind", kind);

		return "product";
	}
	
	@GetMapping("detail/{pseq}")
	public String detail(@PathVariable("pseq") Integer pseq, Model model) {
		var item = productRepo.findById(pseq);
		model.addAttribute("item", item.get());
		return "detail";
	}
	
}
