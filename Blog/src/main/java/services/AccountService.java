package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import models.dao.AccountDao;
import models.entity.Account;

//new objet対象コードなしでもOK
@Service
public class AccountService {
	@Autowired
	private AccountDao accountDao;// DAOのメソッド導入
	// registerロジック処理

	// registerのメールアドレス存在しないなら、new account作成
	public boolean createAccount(String accountEmail, String accountName, String password) {
		if (accountDao.findByAccountEmail(accountEmail) == null) {
			accountDao.save(new Account(accountEmail, accountName, password));
			return true;
		} else {
			return false;
		}
	}

	// loginロジック処理
	// passwordとメールアドレスのマッチのチェック
	public Account loginCheck(String accountEmail, String password) {
		Account account = accountDao.findByAccountEmailAndPassword(accountEmail, password);
		if (account == null) {
			return null;
		} else {
			return account;
		}
	}

}
