package com.sunyd.mockito.v4_v5_stubbing_expect;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

// @RunWith(MockitoJUnitRunner.class)
public class StubbingTest {
	private List<String> list;
	
	@BeforeEach
	public void init_like_cpp_TearUp() {
		this.list = mock(ArrayList.class);
	}
	
	@Test
	public void howToUseStubbing() {
		when(list.get(0)).thenReturn("first");
		Assertions.assertEquals(list.get(0), "first");
		
		when(list.get(anyInt())).thenThrow(RuntimeException.class);
		try {
			list.get(0);
			// fail(); //
		} catch (Exception e) {
			// Assertions.assertTrue(e instanceof RuntimeException.class);
			Assertions.assertEquals(e.getClass(), RuntimeException.class);
		}
	}
	
	@Test
	public void howToUseSubbingVoidMethod() {
		doNothing().when(list).clear();
		list.clear();
		verify(list, times(1)).clear();
		doThrow(RuntimeException.class).when(list).clear();
		try {
			list.clear();
			fail();
		} catch (Exception e) {
			Assertions.assertEquals(e.getClass(), RuntimeException.class);
		}
	}
	
	
	@Test
	public void stubbingDoReturn() {
		// 以下两行写法等价
		when(list.get(0)).thenReturn("first");
		doReturn("second").when(list).get(1);
		
		Assertions.assertEquals(list.get(0), "first");
		Assertions.assertEquals(list.get(1), "second");
	}
	@Test
	public void stubbingWithIterate() {
		when(list.size()).thenReturn(1, 2, 3, 4);
		Assertions.assertEquals(list.size(), 1);
		Assertions.assertEquals(list.size(), 2);
		Assertions.assertEquals(list.size(), 3);
		Assertions.assertEquals(list.size(), 4);
	}

	@Test
	public void stubbingWithAnswer() {
		// when(list.get(anyInt())).thenAnswer(new Answer<Object>() {
		// 	@Override
		// 	public String answer(InvocationOnMock invocationOnMock) throws Throwable {
		// 		Integer index = invocationOnMock.getArgumentAt(0, Integer.class);
		// 		return String.valueOf(index * 10);
		// 	}
		// });
		
		// when(list.get(anyInt())).thenAnswer(invocationOnMock -> {
		// 	Integer index = invocationOnMock.getArgumentAt(0, Integer.class);
		// 	return String.valueOf(index * 10);
		// });
		// Assertions.assertEquals(list.get(0), "0");
		// Assertions.assertEquals(list.get(888), "8880");
	}
	
	@Test
	public void stubbingWithRealCall() {
		StubbingService service = mock(StubbingService.class);
		when(service.runStubbingMethod()).thenReturn("stubbing----------Method");
		Assertions.assertEquals(service.runStubbingMethod(), "stubbing----------Method");
		
		when(service.runRealMethod()).thenCallRealMethod();
		Assertions.assertEquals(service.runRealMethod(), "runRealMethod");
	}
	
	@AfterEach
	public void destroy_like_cpp_TearDown() {
		reset(this.list);
	}
}
