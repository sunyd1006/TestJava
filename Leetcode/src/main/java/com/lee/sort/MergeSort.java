package com.lee.sort;

import org.junit.Test;

import java.util.PriorityQueue;

public class MergeSort {
    public static void main(String[] args) {
        System.out.println("二分排序");

        int[] nums = new int[]{3,2,1,4};
        mergeSort(nums, 0, nums.length-1);
        for (int i : nums) {
            System.out.printf(i + "");
        }
    }

    @Test
    public static void mergeSort(int[] nums, int left, int right){
        if(left<right){
            int mid = (right-left)/2 + left;
            mergeSort(nums, left, mid);
            mergeSort(nums, mid+1, right);
            mergeTwo(nums, left, mid, right);
        }
    }

    public static void mergeTwo(int[] nums, int left, int mid, int right){
        int[] tmp = new int[right-left+1];
        int sz = 0;
        int l=left, r=mid+1;
        while(l<=mid && r<=right){
            tmp[sz++] = nums[l]<nums[r]? nums[l++] : nums[r++];
        }

        while(l<=mid){
            tmp[sz++] = nums[l++];
        }

        while(r<=right){
            tmp[sz++] = nums[r++];
        }

        for (int i = 0; i < sz; i++) {
            nums[left+i] = tmp[i];
        }
    }
}
