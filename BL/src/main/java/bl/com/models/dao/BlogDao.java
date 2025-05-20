package bl.com.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import bl.com.models.entity.Blog;
import jakarta.transaction.Transactional;

@Repository
@Transactional // データエラーの防止
public interface BlogDao extends JpaRepository<Blog, Long> {
	Blog save(Blog blog);

	// 出た結果が多いの場合、Listを使う
	List<Blog> findAll(); // 全てブログの検索

	Blog findByBlogTitle(String blogTitle);

	Blog findByBlogId(Long blogId);

	// accountIdに合ったブログ結果を全て戻る
	List<Blog> findByAccountId(Long accountId); // ユーザーIDでブログ検索
	// 戻る結果がないの場合、voidを使う

	void deleteByBlogId(Long blogId); // ブログIDで削除
}
