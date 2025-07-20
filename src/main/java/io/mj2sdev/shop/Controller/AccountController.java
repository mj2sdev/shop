package io.mj2sdev.shop.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.mj2sdev.shop.model.dto.AccountDTO;
import io.mj2sdev.shop.model.entity.AccountEntity;
import io.mj2sdev.shop.model.mapper.AccountMapper;
import io.mj2sdev.shop.repository.AccountRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AccountController {

	private final AccountRepo accountRepo;
	
	private final AccountMapper accountMapper;

	private final PasswordEncoder passwordEncoder;
	
	@GetMapping("login")
	public String login() {
		return "login";
	}

	@GetMapping("signup")
	String signup(Model model) {
		model.addAttribute("account", new AccountDTO());
		return "signup";
	}

	@PostMapping("signup")
	String signup(@ModelAttribute AccountDTO dto, RedirectAttributes redirectAttributes) {
		String rawPassword = dto.getPassword();
		String cryptedPassword = passwordEncoder.encode(rawPassword);
		dto.setPassword(cryptedPassword);

		AccountEntity account = accountMapper.toEntity(dto);
		
		boolean result = accountRepo.save(account) != null;
		redirectAttributes.addAttribute("result", result);
		
		return "redirect:/signup";
	}
}
