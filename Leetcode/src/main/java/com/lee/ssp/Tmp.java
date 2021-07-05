package com.lee.ssp;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

public class Tmp {
    int[] res;
    int numOfNine = 0, count = 0, start, n;
    char[] num, loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    
    @Test
    public void test(){
        for (int i : printNumbers2(4)) {
            if(i%100==0) System.out.println("");
            System.out.printf(i + " ");
        }
    }
    
    public int[] printNumbers2(int n) {
        this.n = n;
        res = new int[(int)Math.pow(10, n) - 1];
        num = new char[n];
        start = n - 1;      // 在dfs过程中，生成的数字，从左边那个位置开始不为0
        backtrack(0);
        return res;
    }
    void backtrack(int x) {
        if(x == n) {    // num 里面有 n个数字了，
            String s = String.valueOf(num).substring(start);
            if(!s.equals("0")) res[count++] = Integer.parseInt(s);
            if(n - start == numOfNine) start--;      // 如果num里面，全都是9999时，start要左移
            return;
        }
        for(char i : loop) {
            if(i == '9') numOfNine++;
            num[x] = i;
            backtrack(x + 1);
            if(i=='9') numOfNine--;
        }
    }
}
