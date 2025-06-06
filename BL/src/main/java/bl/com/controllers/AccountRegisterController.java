package bl.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bl.com.services.AccountService;

@Controller // Webリクエストの処理
public class AccountRegisterController {
	@Autowired
	private AccountService accountService;// アカウント登録に関するリクエストを管理する

	// 登録画面の表示-Get
	@GetMapping("/account/register")
	public String getAccountRegisterPage() {
		return "register";
	}

	// 登録処理-Post
	@PostMapping("/account/register/process")
	public String accountRegisterProcess(@RequestParam String accountName, @RequestParam String accountEmail,
			@RequestParam String password) {
		if (accountService.createAccount(accountEmail, accountName, password)) {
			return "login";
		} else {
			return "register";
		}
	}
}
