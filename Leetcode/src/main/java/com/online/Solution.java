package com.online;

import java.io.FileNotFoundException;

class Father {
	public static void print(){
		System.out.println("father");
	}
}
public class Solution extends Father {
	public static void main(String[] args) throws FileNotFoundException {
		// Scanner sc = new Scanner(
		// 		new File("/Users/sunyindong/workspace/ForWork/Leetcode/src/main/resources/input.txt"));
		// while (sc.hasNext()){
		// 	//
		// }
		
		Father solution = new Solution();
		solution.print();
	}

	// @Override
	// public static void print(){
	// 	System.out.println("hahah");
	// }
}

