package com.lee.concurrent;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Duo {
    public static void main(String[] args) {
        List<Integer> res = new LinkedList<Integer>(){{ add(1); add(2); add(3);}};
        Integer[]  resArrays = res.toArray(new Integer[res.size()]);
        for (Integer resArray : resArrays) {
            System.out.println(resArray);
        }
    
        for (Integer integer : Arrays.asList(resArrays)) {
            System.out.println(integer);
        }
        
    }
}
