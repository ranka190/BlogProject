package bl.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {
	// テーブルの構成に合わせる
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;
	private String accountName;
	private String accountEmail;
	private String password;

	// JPA用のデフォルトコンストラクタ
	public Account() {
	}

	// 修正：パラメーターの順序を accountEmail, accountName, password に変更
	public Account(String accountEmail, String accountName, String password) {
		this.accountEmail = accountEmail; // 順番に注意
		this.accountName = accountName;
		this.password = password;
	}

	// getter および setter メソッド
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
