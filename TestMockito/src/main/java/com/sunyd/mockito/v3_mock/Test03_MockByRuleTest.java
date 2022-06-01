package com.sunyd.mockito.v3_mock;

import com.sunyd.mockito.common.Account;
import com.sunyd.mockito.common.AccountDao;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;

public class Test03_MockByRuleTest {
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	private AccountDao accountDao;
	
	@Test
	public void testMock() {
		AccountDao accountDao = mock(AccountDao.class);
		Account account = accountDao.findAccount("x", "x");
		System.out.println(account);
	}
}
