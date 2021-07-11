package com.online;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(
                new File("/Users/sunyindong/workspace/ForWork/Leetcode/src/main/resources/input.txt"));
        // // 不带缓冲的
        // Scanner sc = new Scanner(System.in);
        // // 带缓冲的
        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        
        
        while (sc.hasNext()) {
            // sc.nextLine() 读取掉换行符
            
            
        }
    }
    
    class CustomException extends Exception {
        public CustomException(String message) {
            super(message);
        }
    }
}












