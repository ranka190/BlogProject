package bl.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bl.com.models.entity.Account;
import bl.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogRegisterController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private HttpSession session;

	// ブログ登録画面を表示
	@GetMapping("/blog/register")
	public String getBlogRegisterPage(Model model) {
		// sessionからログイン中のユーザー情報をaccountに格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// accountがnullなら再ログイン
		// nullでないなら、ログイン名を表示し、ブログ登録画面へ遷移
		if (account == null) {
			return "redirect:/account/login";
		} else {
			model.addAttribute("accountName", account.getAccountName());
			return "blog_register";
		}
	}

	// ブログの登録処理
	@PostMapping("/blog/register/process")
	public String blogRegisterProcess(@RequestParam String blogTitle, @RequestParam String blogCategory,
			@RequestParam MultipartFile blogImage, @RequestParam String blogDescription) {
		Account account = (Account) session.getAttribute("loginAccountInfo");

		if (account == null) {
			return "redirect:/account/login";
		} else {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (blogService.createBlog(blogCategory, blogDescription, fileName, blogTitle, account.getAccountId())) {
				return "redirect:/blog/list";
			} else {
				return "blog_register";
			}
		}
	}
}
