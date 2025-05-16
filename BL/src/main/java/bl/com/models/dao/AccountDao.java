package bl.com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bl.com.models.entity.Account;

//Accountテーブルのデータベース操作機能
@Repository // コードからSQL操作のインターフェス
public interface AccountDao extends JpaRepository<Account, Long> {
	Account save(Account account);// 保存機能

	Account findByAccountEmail(String accountEmail);// メールアドレス存在確認

	Account findByAccountEmailAndPassword(String accountEmail, String password);// メールアドレスとパスワードマッチの検査
}
