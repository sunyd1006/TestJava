package com.javaguide.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
    // 处理文件的数量
    private static final int threadCount = 6;

    public static void main(String[] args) throws InterruptedException {
        // 创建一个具有固定线程数量的线程池对象（推荐使用构造方法创建）
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadnum = i;
            threadPool.execute(() -> {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                    //表示一个文件已经被完成, 计数器减一
                    countDownLatch.countDown();
                }
            });
        }
        System.out.println("执行完了吗？");
        
        // 阻塞，直到 countDownLatch.getCount() == 0
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("执行完了！");
    }

}




