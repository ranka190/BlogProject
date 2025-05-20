package bl.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bl.com.models.dao.AccountDao;
import bl.com.models.entity.Account;

@Service // new object対象コードなしでもOK
public class AccountService {
	@Autowired // new AccountDao対象コードなしでもOK
	private AccountDao accountDao;// DAOのメソッド導入
	// registerロジック処理
	// registerのメールアドレス存在しないなら、new account作成

	public boolean createAccount(String accountName, String accountEmail, String password) {
		if (accountDao.findByAccountEmail(accountEmail) == null) {
			accountDao.save(new Account(accountName, accountEmail, password));
			return true;// 登録成功
		} else {
			return false;// 登録失敗
		}
	}

	// loginロジック処理
	// passwordとメールアドレスのマッチのチェック
	public Account loginCheck(String accountEmail, String password) {
		// アカウントデータを入れる
		Account account = accountDao.findByAccountEmailAndPassword(accountEmail, password);
		// アカウントを見つけたら、return
		if (account == null) {
			return null;
		} else {
			return account;
		}
	}

}
