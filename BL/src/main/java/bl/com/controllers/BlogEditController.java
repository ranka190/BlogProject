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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bl.com.models.entity.Account;
import bl.com.models.entity.Blog;
import bl.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogEditController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private HttpSession session;

	// 編集画面の表示
	@GetMapping("/blog/edit/{blogId}")
	public String getBlogEditPage(@PathVariable Long blogId, Model model) {
		// sessionからAccountの情報を取得する
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// アカウントがnullの場合、ログイン画面にリダイレクト
		if (account == null) {
			return "redirect:/account/login";
		} else {
			// 編集画面に表示する情報を取得
			Blog blog = blogService.blogEditCheck(blogId);
			// blogがnullの場合は一覧画面へ
			// そうでない場合、編集内容を編集画面に渡す
			if (blog == null) {
				return "redirect:/blog/list";
			} else {
				model.addAttribute("accountName", account.getAccountName());
				model.addAttribute("blog", blog);
				return "blog_edit";
			}
		}
	}

	// 更新処理をする
	@PostMapping("/blog/edit/process")
	public String blogUpdate(@RequestParam String blogTitle, @RequestParam String categoryName,
			@RequestParam MultipartFile blogImage, @RequestParam String article, @RequestParam Long blogId) {
		// セッションからログイン中のユーザー情報をadminという変数に格納
		Account account = (Account) session.getAttribute("loginAccountInfo");
		// adminがnullの場合、ログイン画面へリダイレクト
		// そうでない場合、
		if (account == null) {
			return "redirect:/account/login";
		} else {
			/**
			 * 現在の日時情報を元にファイル名を作成する。
			 * SimpleDateFormatクラスを使って"yyyy-MM-dd-HH-mm-ss-"形式に整形した文字列と、
			 * blogImageオブジェクトから取得した元のファイル名を連結し、fileNameに代入する。
			 */
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())
					+ blogImage.getOriginalFilename();
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
			} catch (IOException e) {
				// 自動生成されたcatchブロック
				e.printStackTrace();
			}
			// blogUpdateの結果がtrueならブログ一覧にリダイレクト
			// そうでなければ編集画面にリダイレクト
			if (blogService.blogUpdate(blogId, categoryName, article, fileName, blogTitle, account.getAccountId())) {
				return "redirect:/blog/list";
			} else {
				return "redirect:/blog/edit" + blogId;
			}
		}
	}
}
