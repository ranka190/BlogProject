package bl.com.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import bl.com.services.AccountService;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountRegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    // サービスクラスを使ったデータ作成
    @BeforeEach
    public void prepareData() {
        // Mock設定は各テストメソッド内で個別に行う
        // ここでは共通の初期化処理のみ実行
    }

    // Test No.1: 画面表示初期表示 - 登録画面が正常表示できるテスト
    @Test
    public void testGetRegisterPage_Succeed() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/account/register");
        
        mockMvc.perform(request)
                .andExpect(view().name("register"));
    }

    // Test No.2: ユーザー登録処理 - ユーザーの登録が成功するかのテスト  
    @Test
    public void testAccountRegister_NewAccount_Succeed() throws Exception {
        // 成功ケース用のモック設定を上書き
        when(accountService.createAccount("test@test.com", "yanxiaoou", "1234")).thenReturn(true);
        
        RequestBuilder request = MockMvcRequestBuilders
                .post("/account/register/process")
                .param("accountName", "yanxiaoou")
                .param("accountEmail", "test@test.com")  
                .param("password", "1234");

        mockMvc.perform(request)
                .andExpect(view().name("login"));
        
        // accountService.createAccountメソッドが指定された引数で呼び出されたことを検証
        verify(accountService, times(1)).createAccount("test@test.com", "yanxiaoou", "1234");
    }

    // Test No.3: ユーザー登録テスト - ユーザーの登録が失敗するかのテスト
    @Test
    public void testAccountRegister_Process_Fail() throws Exception {
        // 失敗ケース用のモック設定
        when(accountService.createAccount("test@test.com", "yanxiaoou", "1234")).thenReturn(false);
        
        RequestBuilder request = MockMvcRequestBuilders
                .post("/account/register/process")
                .param("accountName", "yanxiaoou")
                .param("accountEmail", "test@test.com")
                .param("password", "1234");

        mockMvc.perform(request)
                .andExpect(view().name("register"));
        
        // accountService.createAccountメソッドが呼び出されたことを検証
        verify(accountService, times(1)).createAccount("test@test.com", "yanxiaoou", "1234"); 
    }

    // Test No.4: 画面表示初期表示 - ブラウザーのURL直接アクセステスト
    @Test
    public void testBrowserUrlAccess_DirectAccess_Succeed() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/account/register");
        
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
        
        // メールアドレス及びパスワードの入力欄が空白になっていることを検証
        // （実際のHTMLの検証は統合テストで行う）
    }
}