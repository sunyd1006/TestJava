package com.lee.ssp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;
import java.util.*;

public class TestFunction {
    
    public static void main(String[] args) throws IOException {
        TestFunction testFunction = new TestFunction();
        System.out.println((1 << 0));
        
        System.out.println(testFunction.lengthOfLongestSubstring("abcabcbb"));
    }
    
    
    // ————————————————————————————————————————————————————————
    @ParameterizedTest
    @ValueSource(strings = "abcabcd")
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int res = 0, tmp = 0;
        for (int j = 0; j < s.length(); j++) {
            int i = dic.getOrDefault(s.charAt(j), -1); // 获取索引 i
            dic.put(s.charAt(j), j); // 更新哈希表
            tmp = tmp < j - i ? tmp + 1 : j - i; // dp[j - 1] -> dp[j]
            res = Math.max(res, tmp); // max(dp[j - 1], dp[j])
        }
        return res;
    }
    
    class CustomException extends Exception {
        public CustomException(String message) {
            super(message);
        }
    }
}

