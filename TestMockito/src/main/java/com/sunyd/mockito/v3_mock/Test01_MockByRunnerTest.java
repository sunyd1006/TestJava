package com.sunyd.mockito.v3_mock;


import com.sunyd.mockito.common.Account;
import com.sunyd.mockito.common.AccountDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class Test01_MockByRunnerTest {
	
	@Test
	public void testMock() {
		AccountDao accountDao = mock(AccountDao.class, Mockito.RETURNS_SMART_NULLS);
		Account account = accountDao.findAccount("x", "x");
		System.out.println(account);
	}
}
