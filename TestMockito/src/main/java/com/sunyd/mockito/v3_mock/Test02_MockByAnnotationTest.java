package com.sunyd.mockito.v3_mock;

import com.sunyd.mockito.common.Account;
import com.sunyd.mockito.common.AccountDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class Test02_MockByAnnotationTest {
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Mock(answer = Answers.RETURNS_SMART_NULLS)
	private AccountDao accountDao;
	
	@Test
	public void testMock() {
		Account account = accountDao.findAccount("x", "x");
		System.out.println(account);
	}
}
