package io.mj2sdev.shop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.mj2sdev.shop.repository.ProductRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
	
	private final ProductRepo productRepo;

	private final WebClient webClient;

	@Value("${external.api.key}")
	private String apiKey;

	@GetMapping
	public String index(Model model) {
		var list = productRepo.findTop5ByOrderByCreatedAtDesc();
		model.addAttribute("list", list);

		return "index";
	}

	@GetMapping("question")
	@ResponseBody
	String question(@RequestParam String q) {
		String jsonPayload = """
				{
				  "contents": [
				    {
				      "parts": [
				        {
				          "text": "%s"
				        }
				      ]
				    }
				  ]
				}
			""".formatted(q);

		String response = webClient.post()
			.uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent")
			.headers(header -> {
				header.add("X-goog-api-key", apiKey);
				header.add("Content-Type", "application/json");
			})
			.bodyValue(jsonPayload)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(response);
			String text = root
				.path("candidates").get(0)
				.path("content")
				.path("parts").get(0)
				.path("text")
				.asText();

			return text;
		} catch (Exception e) { e.printStackTrace(); return "오류가 발생하였습니다.^^"; }
	}
	
}
