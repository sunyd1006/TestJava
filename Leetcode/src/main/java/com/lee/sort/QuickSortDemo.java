package com.lee.sort;

import org.junit.Test;

public class QuickSortDemo {
    public static void main(String[] args) {
        QuickSortDemo quickSortDemo = new QuickSortDemo();
        
        int[] nums = {1, 3, 5, 2, 4, 6};
        if (nums == null) return;
        quickSortDemo.quickSort(nums, 0, nums.length-1);
        
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }
    
    public void quickSort(int[] nums, int left, int right) {
        // note: while(left<right)
        if (left < right) {
            int mid = partition(nums, left, right);
            quickSort(nums, left, mid - 1);
            quickSort(nums, mid + 1, right);
        }
    }
    
    public int partition(int[] nums, int left, int right) {
        int x = nums[left];
        while (left < right) {
            while (left < right && x <= nums[right]) right--;
            nums[left] = nums[right];
            
            while (left < right && x >= nums[left]) left++;
            nums[right] = nums[left];
        }
        nums[left] = x;
        return left;
    }
}
