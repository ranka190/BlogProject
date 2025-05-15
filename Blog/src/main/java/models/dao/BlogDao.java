package models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional // データエラーの防止
public interface BlogDao extends JpaRepository<BlogDao, Long> {
	BlogDao save(BlogDao blog);

	List<BlogDao> findAll();// 全てブログの検索

	BlogDao findByBlogTitle(String blogTitle);// ブログのタイトルで検索

	BlogDao findByBlogId(Long blogId);

	void deleteByBlogId(Long blogId);// ブログIDで削除

}
