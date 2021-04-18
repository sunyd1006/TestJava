package com.javaguide.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Snailclimb
 * @date 2018年10月1日
 * @Description: 测试 CyclicBarrier 类中带参数的 await() 方法
 */

// https://snailclimb.gitee.io/javaguide/#/docs/java/multi-thread/AQS%E5%8E%9F%E7%90%86%E4%BB%A5%E5%8F%8AAQS%E5%90%8C%E6%AD%A5%E7%BB%84%E4%BB%B6%E6%80%BB%E7%BB%93?id=_52-cyclicbarrier-%e7%9a%84%e4%bd%bf%e7%94%a8%e7%a4%ba%e4%be%8b
public class CyclicBarrierDemo {
    // 请求的数量, 相当于threadCount个任务
    private static final int threadCount = 550;
    // 需要同步的线程数量
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            threadPool.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }

    public static void test(int threadnum) throws InterruptedException, BrokenBarrierException {
        System.out.println("threadnum:" + threadnum + "is ready");
        try {
            /**等待60秒，保证子线程完全执行结束*/
            cyclicBarrier.await(60, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("-----CyclicBarrierException------");
        }
        System.out.println("threadnum:" + threadnum + "is finish");
    }

}
