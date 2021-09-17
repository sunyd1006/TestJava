package com.tmp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestDemo {
	
	@Test
	public void test() {
		int[] nums = new int[]{0, 2, 1 , 1, 3, 4};
		quickSort(nums, 0, nums.length-1);
		for (Integer num : nums) {
			System.out.print(num + " ");
		}
		
		ArrayList<Integer> integers = GetLeastNumbers_Solution(nums, 3);
		for (Integer num : integers) {
			System.out.print(num + " ");
		}
	}
	
	public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
		if (input == null || k > input.length) return null;
		quickSort(input, 0, input.length - 1);
		
		for (int i : input) {
			System.out.print(i + " ");
		}
		
		ArrayList<Integer> res = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			res.add(input[i]);
		}
		return res;
	}
	
	public void quickSort(int[] input, int left, int right) {
		if (left < right) {
			int x = partition(input, left, right);
			System.out.println("cur p: " + x);
			quickSort(input, left, x - 1);
			quickSort(input, x + 1, right);
		}
	}
	
	public int partition(int[] input, int left, int right) {
		int x = input[0];
		while (left < right) {
			while (left < right && x <= input[right]) {
				right--;
			}
			input[left] = input[right];
			
			while (left < right && input[left] <= x) {
				left++;
			}
			input[right] = input[left];
		}
		input[left] = x;
		return left;
	}
}
