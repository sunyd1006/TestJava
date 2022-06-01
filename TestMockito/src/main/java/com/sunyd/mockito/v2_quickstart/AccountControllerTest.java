package com.sunyd.mockito.v2_quickstart;

import com.sunyd.mockito.common.Account;
import com.sunyd.mockito.common.AccountDao;
import com.sunyd.mockito.common.AccountLoginController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;

public class AccountControllerTest {
	private AccountDao accountDao;
	private HttpServletRequest request;
	private AccountLoginController accountLoginController;
	
	@BeforeEach
	public void setUp() {
		this.accountDao = Mockito.mock(AccountDao.class);
		this.request = Mockito.mock(HttpServletRequest.class);
		this.accountLoginController = new AccountLoginController(accountDao);
	}

	@Test
	public void testLogin() {
		Account account = new Account();
		Mockito.when(request.getParameter("username")).thenReturn("alex");
		Mockito.when(request.getParameter("password")).thenReturn("123123");
		
		String result;
		
		// controller.login() with login
		Mockito.when(accountDao.findAccount(anyString(), anyString())).thenReturn(null);
		 result = accountLoginController.login(request);
		Assertions.assertEquals(result, "/login");
	
		// controller.login() with index
		Mockito.when(accountDao.findAccount(anyString(), anyString())).thenReturn(account);
		result = accountLoginController.login(request);
		Assertions.assertEquals(result, "/index");
		
		
		// controller.login() with exception
		Mockito.when(accountDao.findAccount(anyString(), anyString())).thenThrow(UnsupportedOperationException.class);
		result = accountLoginController.login(request);
		Assertions.assertEquals(result, "/505");
	}

	
	
}
