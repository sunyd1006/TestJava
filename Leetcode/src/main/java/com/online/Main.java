package com.online;

import java.util.LinkedList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		String[] input = {"abcee", "bdefe", "ebvfe"};
		List<Character> res = findCommon(input);
		for (Character one : res) {
			System.out.print(one);
		}
		System.out.println("");
		
		String[] input2 = {"aa", "bb"};
		res = findCommon(input2);
		for (Character one : res) {
			System.out.print(one);
		}
		System.out.println("");
		
	}
	
	public static List<Character> findCommon(String[] input) {
		if (input == null || input.length == 0) return null;
		
		int sz = input.length;
		int[][] map = new int[sz][26];
		for (int i = 0; i < sz; i++) {
			char[] chars = input[i].toCharArray();
			for (int j = 0; j < chars.length; j++) {
				int cur = chars[j] - 'a';
				map[i][cur] = map[i][cur] + 1;
			}
		}
		
		List<Character> res = new LinkedList<>();
		for (int j = 0; j < 26; j++) {
			int minValue = Integer.MAX_VALUE;
			for (int i = 0; i < sz; i++) {
				minValue = map[i][j] < minValue ? map[i][j] : minValue;
			}
			while ((minValue--) > 0) {
				res.add((char) ('a' + j));
			}
		}
		return res;
	}
}