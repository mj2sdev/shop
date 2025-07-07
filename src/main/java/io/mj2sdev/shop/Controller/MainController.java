package io.mj2sdev.shop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/")
public class MainController {
	
	@GetMapping
	public String index() {
		System.out.println("hello world");
		System.out.println("stress test");
		return "index";
	}
	
}
