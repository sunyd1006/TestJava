package com.ssp;


import org.junit.jupiter.api.Test;

import java.util.*;

public class Solution {
	
	@Test
	public void test(){
		int num = 9;
		System.out.println(getBit(num, 0));
		System.out.println(getBit(num, 1));
		System.out.println(getBit(num, 2));
		System.out.println(getBit(num, 3));
		// System.out.println(9&8);
	}
	public int getBit(int num, int i){
		return (num>>>i) & 1;
	}
}