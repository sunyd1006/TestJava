package com.java.thread;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SunProducerAndConsumer {
    private static AtomicInteger num = new AtomicInteger();
    private static final int MAX_LEN = 10;
    private Deque<Integer> pool = new LinkedList<>();

    public void produce(){
        while (true){                             // 生产者要一直生产
            try{
                Thread.sleep(3000);         // 控制生产速率
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            synchronized(pool){
                while (pool.size()==MAX_LEN){
                    try {
                        pool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                pool.add(num.incrementAndGet());
                System.out.println(Thread.currentThread() +"] 生产" + num.get() + " [poll size]: " + pool.size());
                pool.notifyAll();
            }
        }
    }


    public void consume(){
        while(true){                             // 消费者要一直消费

            try{
                Thread.sleep(3000);         // 控制消费速率
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            synchronized (pool){
                while (pool.size() == 0){
                    try {
                        pool.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                int used = pool.pollFirst();
                System.out.println(Thread.currentThread() +"] 消费" + used + " [poll size]: " + pool.size());
                pool.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        SunProducerAndConsumer sunProducerAndConsumer = new SunProducerAndConsumer();

        for (int i=0; i<5; i++){
            new Thread(()->{
                sunProducerAndConsumer.produce();
            }, "S"+ i).start();
        }

        for (int i=0; i<3; i++){
            new Thread(()->{
                sunProducerAndConsumer.consume();
            }, "C" + i).start();
        }
    }
}
