package io.mj2sdev.shop.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.mj2sdev.shop.model.dto.AccountDTO;
import io.mj2sdev.shop.model.dto.ResponseDTO;
import io.mj2sdev.shop.service.AccountService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AccountController {

	private final AccountService accountService;
	
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
		ResponseDTO response = accountService.insertAccount(dto);
		redirectAttributes.addFlashAttribute("message", response.getMessage());
		return response.getResult() ? "redirect:/" : "redirect:/signup";
	}

	@GetMapping("mypage")
	void mypage(@AuthenticationPrincipal AccountDTO account, Model model) {
		AccountDTO findedAccount = accountService.findById(account);
		model.addAttribute("account", findedAccount);
	}

	@PostMapping("mypage")
	String mypage(
		@AuthenticationPrincipal AccountDTO account,
		@ModelAttribute("account") AccountDTO changedAccountInfo,
		RedirectAttributes redirectAttributes) {
		
		ResponseDTO response = accountService.updateAccount(account, changedAccountInfo);
		
		redirectAttributes.addAttribute("message", response.getMessage());
		
		return "redirect:/mypage";
	}
}
