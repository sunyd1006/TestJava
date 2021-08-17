package com.design;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MultipleProducerAndConsumerDemo {
    private static AtomicInteger num = new AtomicInteger();
    private static final int MAX_LEN = 10;
    private Deque<Integer> pool = new LinkedList<>();

    public static void main(String[] args) {
        MultipleProducerAndConsumerDemo sunProducerAndConsumer = new MultipleProducerAndConsumerDemo();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                sunProducerAndConsumer.produce();
            }, "S" + i).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                sunProducerAndConsumer.consume();
            }, "C" + i).start();
        }
    }

    public void produce() {
        while (true) {                             // 生产者要一直生产
            try {
                Thread.sleep(3000);               // 控制生产速率
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized(pool) {
                // note, 这里用 if 是错误的
                while (pool.size() == MAX_LEN) {
                    try {
                        System.out.println("pool is full, please wait.");
                        pool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                pool.add(num.incrementAndGet());
                System.out.println(Thread.currentThread()  + " produce ===> : "+ num.get()+ " [poll size]: " + pool.size());
                pool.notifyAll();
            }
        }
    }

    public void consume() {
        while (true) {                             // 消费者要一直消费
            try {
                Thread.sleep(2000);         // 控制消费速率
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(pool) {
                while (pool.size() == 0) {
                    try {
                        System.out.println("pool is empty, please wait.");
                        pool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int used = pool.pollFirst();
                System.out.println(Thread.currentThread()  + " ===> consume : "+ used+" [poll size]: " + pool.size());
                pool.notifyAll();
            }
        }
    }
}