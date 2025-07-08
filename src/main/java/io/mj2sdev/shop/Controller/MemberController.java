package io.mj2sdev.shop.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.mj2sdev.shop.model.Account;
import io.mj2sdev.shop.repository.AccountRepo;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MemberController {

	private final AccountRepo accountRepo;
	
	@GetMapping("login")
	public String login() {
		return "login";
	}

	@GetMapping("signup")
	String signup(Model model) {
		model.addAttribute("account", new Account());
		return "signup";
	}

	@PostMapping("signup")
	String signup(@ModelAttribute Account account, RedirectAttributes redirectAttributes) {
		String salt = BCrypt.gensalt();
		String rawPassword = account.getPassword();
		String cryptedPassword = BCrypt.hashpw(rawPassword, salt);
		account.setPassword(cryptedPassword);
		Account saved = accountRepo.save(account);
		
		boolean result = saved.getUserid() != null;
		redirectAttributes.addAttribute("result", result);
		
		return "redirect:/";
	}
}
