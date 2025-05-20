package bl.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bl.com.models.entity.Account;

@Repository // データアクセス層 データベースの例外処理をSpringの例外に統一する
//コードからSQL操作のインターフェス
public interface AccountDao extends JpaRepository<Account, Long> {// テーブル名と主キー型
	Account save(Account account);// 保存機能

	Account findByAccountEmail(String accountEmail);// メールアドレスでアカウント確認

	Account findByAccountEmailAndPassword(String accountEmail, String password);// メールアドレスとパスワードマッチの検査
}
