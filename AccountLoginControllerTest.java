package bl.com.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import bl.com.models.entity.Account;
import bl.com.services.AccountService;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountLoginControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private AccountService accountService;
    
    @BeforeEach
    public void prepareData() {
        // ログインが成功：正しいメールアドレスとパスワード
        Account mockAccount = new Account();
        mockAccount.setAccountEmail("test@test.com");
        mockAccount.setAccountName("yanxiaoou");
        when(accountService.loginCheck("test@test.com", "1234")).thenReturn(mockAccount);
        
        // ログインが失敗：間違ったメールアドレス  
        when(accountService.loginCheck(eq("yan@test.com"), any())).thenReturn(null);
        
        // ログインが失敗：間違ったパスワード
        when(accountService.loginCheck(eq("test@test.com"), eq("1234abcd"))).thenReturn(null);
    }
    
    @Test
    public void testGetLoginPage_Succeed() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/account/login");
        mockMvc.perform(request).andExpect(view().name("login"));
    }
    
    @Test
    public void testLogin_ValidCredentials_Succeed() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/account/login/process")
                .param("accountEmail", "test@test.com")
                .param("password", "1234");
        
        mockMvc.perform(request)
                .andExpect(redirectedUrl("/blog/list"));
    }
    
    @Test
    public void testLogin_InvalidEmail_Fail() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/account/login/process")
                .param("accountEmail", "yan@test.com")
                .param("password", "1234");
        
        mockMvc.perform(request)
                .andExpect(view().name("login"));
    }
    
    @Test
    public void testLogin_InvalidCredentials_Fail() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/account/login/process")
                .param("accountEmail", "test@test.com")
                .param("password", "1234abcd");
        
        mockMvc.perform(request)
                .andExpect(view().name("login"));
    }
    
    @Test
    public void testLogin_InvalidPassword_Fail() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post("/account/login/process")
                .param("accountEmail", "test@test.com")
                .param("password", "1234abcd");
        
        mockMvc.perform(request)
                .andExpect(view().name("login"));
    }
    
    @Test
    public void testGetLoginPage_InitialDisplay() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/account/login");
        mockMvc.perform(request).andExpect(view().name("login"));
    }
}