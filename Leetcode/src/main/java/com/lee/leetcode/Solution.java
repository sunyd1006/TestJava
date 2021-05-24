package com.lee.leetcode;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.*;

import com.lee.TreeNode;
import com.lee.ListNode;

import javax.xml.bind.annotation.XmlInlineBinaryData;

public class Solution {
    
    @Test
    public void thisIsTest() {
        System.out.println(convert("PAYPALISHIRING", 3));
    }
    
    public String convert(String s, int numRows) {
        if (s == null || numRows < 0) return s;
        StringBuilder[] list = new StringBuilder[numRows];
        for (int i = 0; i < list.length; i++) {
            list[i] = new StringBuilder();
        }
        
        int j = 0, derection = 1;
        for (int i = 0; i <s.length() ; i++) {
            list[j].append(s.charAt(i));
            if (j == numRows - 1 || derection == -1 && j > 0) derection = -1;
            else if (j == 0 || derection == 1 && j < numRows - 1) derection = 1;
            j += derection;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder stringBuilder : list) {
          res.append(stringBuilder.toString());
        }
        return res.toString();
    }
    
    /*
    ----------------------------------------------------------------------------------------------------
     */
    
    Integer count, res;
    
    public int findKthNumber(int n, int k) {
        if (k > n || n < 1) return -1;
        int start = 1;
        count = 1;
        res = 1;
        
        return res;
    }
    
    public void backtrack(int n, int k, int curRoot, String tmp) {
        int cur = Integer.parseInt(tmp), curLen = tmp.length();
        int nLen = Integer.toString(n).length();
        if (curLen > nLen || cur > n) {
            count--;
            return;
        } else if (count == k) {
            res = cur;
            return;
        } else {
            for (int i = 0; i < 10; i++) {
                if (cur > n) break;
                count++;
                if (curLen == nLen) {
                    cur++;
                    backtrack(n, k, curRoot, cur + "");
                } else {
                    backtrack(n, k, curRoot, tmp + i);
                }
                
                if (i == 9 && count < k) {
                    // backtrack(n, k, curRoot, );
                }
            }
        }
    }
    
}