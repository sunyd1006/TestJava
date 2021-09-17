package com.ssp;


import org.springframework.jdbc.core.metadata.HsqlTableMetaDataProvider;

public class Main {
	public static void main(String[] args) {
		System.out.println(findMin("aaaaabacddd", "bac"));
	}
	
	public static String findMin(String s1, String s2) {
		if (s2 == null || s2.length() == 0) {
			return "";
		}
		
		int[] need = new int[128];
		int[] have = new int[128];
		int diffChars = 0;
		for (char c : s2.toCharArray()) {
			if (need[c] == 0) {
				diffChars++;
			}
			need[c]++;
		}
		
		int left = 0, right = 0, minLen = Integer.MAX_VALUE;
		int start = 0, end = 0, curChars = 0;
		while (right < s1.length()) {
			char item = s1.charAt(right++);
			have[item]++;
			
			if (have[item] == need[item]) {
				curChars++;
			}
			
			while (curChars >= diffChars) {
				if (right - left < minLen) {
					start = left;
					end = right;
				}
				char move = s1.charAt(left++);
				have[move]--;
				if (need[move] > have[move]) {
					curChars--;
				}
			}
		}
		return s1.substring(start, end);
	}
}