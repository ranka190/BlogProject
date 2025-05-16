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

	// ブログ一覧チェック
	public List<Blog> selectAllBlogList(Long accountId) {
		if (accountId == null) {
			return null;
		} else {
			return blogDao.findAll();
		}
	}

	// ブログ登録処理チェック
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

}
