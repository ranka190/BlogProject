package bl.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import bl.com.models.entity.Account;
import bl.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogDeleteController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private HttpSession session;

	@PostMapping("/blog/delete")
	public String blogDelete(Long blogId) {
		// セッションからログインしている人の情報をadminという変数に格納
		Account account = (Account) session.getAttribute("loginAccountInfo");

		// もしaccount == null ログイン画面にリダイレクトする
		if (account == null) {
			return "redirect:/account/login";
		} else {
			// もし、deleteProductの結果がtrueだったら
			if (blogService.blogDelete(blogId)) {
				// 商品の一覧ページにリダイレクト
				return "redirect:/blog/list";
			} else {
				// そうでない場合
				// 編集画面にリダイレクトする
				return "redirect:/blog/edit/" + blogId;
			}
		}
	}
}
