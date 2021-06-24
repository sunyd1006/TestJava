package com.java.lock;


import lombok.SneakyThrows;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

/**
 * 用一个方法定时更新特定值，定时操作：https://www.jianshu.com/p/db1611a16cb8
 * 一个线程去打印值
 */
public class SynchronizedDemo {
    static int[] array = {1,2,3,4,5};
    public static void main(String[] args) {
        new Timer("timer - ").schedule(new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("开始改变 array int[] 的值");

                // 并没有保证原子操作， array[i] 的顺序还是[5，2，3，4，5]
                synchronized(SynchronizedDemo.class) {
                    for (int i = 0; i < 5; i++) {
                        array[i] = array[i]*5;

                        sleep(5000);
                    }
                }


                System.out.println("结束改变 array int[] 的值");
            }
        }, 1000);

        while (true){
            new Thread(()->{
                for (int i = 0; i < 5; i++) {
                    System.out.print(array[i] + " ");
                }
                System.out.println(" ");
            }).start();
        }
    }
}
