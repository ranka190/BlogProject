package bl.com.models.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {
    // テーブルに合わせる
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;
    private String accountName;
    private String accountEmail;
    private String password;
    
    // JPA用コンストラクタ
    public Account() {
    }
    
    // 修正：参数顺序改为 accountEmail, accountName, password
    public Account(String accountEmail, String accountName, String password) {
        this.accountEmail = accountEmail;    // 注意顺序
        this.accountName = accountName;
        this.password = password;
    }
    
    // getter setter メソッド
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