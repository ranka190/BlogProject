package bl.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import bl.com.models.entity.Account;
import bl.com.models.entity.Blog;
import bl.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogListController {
	@Autowired
	private HttpSession session;

	@Autowired
	private BlogService blogService;

	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
		// sessionからログイン情報を取得
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// 管理者がnullの場合
		// nullでない場合
		if (account == null) {
			return "redirect:/account/login";
		} else {
			// 商品情报取得
			List<Blog> blogList = blogService.selectAllBlogList(account.getAccountId());
			model.addAttribute("accountName", account.getAccountName());
			model.addAttribute("blogList", blogList);
			return "blog_list";
		}
	}
}
