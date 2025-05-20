package bl.com.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // データベースのテーブルと合わせる
public class Account {
	@Id // private Long accountIdは主キーを示す
	@GeneratedValue(strategy = GenerationType.AUTO) // idを自動的に付ける
	private Long accountId;
	private String accountName;
	private String accountEmail;
	private String password;

	// JPA用のデフォルトコンストラクタ
	public Account() {
	}

	public Account(String accountEmail, String accountName, String password) {
		this.accountEmail = accountEmail;
		this.accountName = accountName;
		this.password = password;
	}

	// getterおよびsetterメソッド
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
