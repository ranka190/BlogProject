package bl.com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bl.com.models.dao.BlogDao;
import bl.com.models.entity.Blog;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

	// ブログ一覧の取得チェック
	public List<Blog> selectAllBlogList(Long accountId) {
		if (accountId == null) {
			return null;
		} else {
			return blogDao.findAll();
		}
	}

	// ブログ登録処理のチェック
	public boolean createBlog(String blogCategory, String blogDescription, String blogImage, String blogTitle,
			Long accountId) {
		if (blogDao.findByBlogTitle(blogTitle) == null) {
			Blog blog = new Blog(blogTitle, blogCategory, blogImage, blogDescription, accountId);
			blogDao.save(blog);
			return true;
		} else {
			return false;
		}
	}

	// 編集画面を表示する際のチェック処理
	// blogIdがnullの場合はnullを返す
	// nullでない場合、findByBlogIdの結果をコントローラーに返す
	public Blog blogEditCheck(Long blogId) {
		if (blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}

	public boolean blogUpdate(Long blogId, String categoryName, String article, String blogImage, String blogTitle,
			Long accountId) {
		if (blogId == null) {
			return false;
		} else {
			Blog blog = blogDao.findByBlogId(blogId);
			blog.setCategoryName(categoryName);
			blog.setArticle(article);
			blog.setBlogImage(blogImage);
			blog.setBlogTitle(blogTitle);
			blog.setBlogId(blogId);
			blogDao.save(blog);
			return true;
		}
	}
	// 削除処理のチェック
	public boolean blogDelete(Long blogId) {
		if (blogId == null) {
			return false;
		} else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
}
