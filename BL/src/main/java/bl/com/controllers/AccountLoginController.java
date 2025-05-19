package bl.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bl.com.models.entity.Account;
import bl.com.services.AccountService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AccountLoginController {
	@Autowired
	private AccountService accountService;
	// sessionを使用可能にする宣言
	@Autowired
	private HttpSession session;

	// ログイン画面の表示
	@GetMapping("/account/login")
	public String getAccountLoginPage() {
		return "login";
	}

	// ログイン処理
	@PostMapping("/account/login/process")
	public String accountLoginProcess(@RequestParam String accountEmail, @RequestParam String password) {
		// loginCheckメソッドを呼び出し、accountという変数に格納
		Account account = accountService.loginCheck(accountEmail, password);
		// accountがnullの場合、ログイン画面にとどまる
		// そうでない場合、sessionにログイン情報を保存
		if (account == null) {
			return "login";
		} else {
			session.setAttribute("loginAccountInfo", account);
			return "redirect:/blog/list";
		}
	}
}
