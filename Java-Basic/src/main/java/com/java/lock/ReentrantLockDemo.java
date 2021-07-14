package com.java.lock;

import org.junit.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// https://www.cnblogs.com/takumicx/p/9338983.html
public class ReentrantLockDemo {
    private static ReentrantLock fairLock = new ReentrantLock(true);
    private static ReentrantLock nonfairLock = new ReentrantLock();
    
    @Test
    /**
     * 测试是否可重入
     */
    public void isReenterable() {
        ReentrantLock lock = new ReentrantLock();
        for (int i = 1; i <= 3; i++) {
            lock.lock();
        }
        for (int i = 1; i <= 3; i++) {
            try {
            
            } finally {
                lock.unlock();
            }
        }
    }
    
    private void test() {
        nonfairLock.lock();
        System.out.println(Thread.currentThread() + " 进行原子操作");
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            nonfairLock.unlock();
        }
    }
    
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ReentrantLockDemo demo = new ReentrantLockDemo();
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Thread(demo::test));
        }
    }
    
    static ReentrantLock lock1 = new ReentrantLock();
    static ReentrantLock lock2 = new ReentrantLock();
    @Test
    public void testInterruptibly()  {
        try{
            Thread thread1 = new Thread(new ThreadDemo(lock1, lock2));//该线程先获取锁1,再获取锁2
            Thread thread2 = new Thread(new ThreadDemo(lock2, lock1));//该线程先获取锁2,再获取锁1
            thread1.start();
            thread2.start();
            Thread.sleep(1000); // 让子线程先跑一会
            
            thread1.interrupt();  //是第一个线程中断
            // thread1.join();
            // thread2.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    static class ThreadDemo implements Runnable {
        ReentrantLock firstLock;
        ReentrantLock secondLock;
        public ThreadDemo(ReentrantLock firstLock, ReentrantLock secondLock) {
            this.firstLock = firstLock;
            this.secondLock = secondLock;
        }
    
        /**
         * 获取锁时自旋1000秒， // 可被中断的锁
         */
        @Override
        public void run() {
            try {
                // firstLock.lockInterruptibly();    // 可被中断的锁
                // TimeUnit.MILLISECONDS.sleep(10);  //更好的触发死锁
                // secondLock.lockInterruptibly();
    
                while(!lock1.tryLock(1000, TimeUnit.MILLISECONDS)){          // 获取锁时限时等待, tryLock(10)
                    TimeUnit.MILLISECONDS.sleep(10);
                }
                while(!lock2.tryLock()){
                    lock1.unlock();
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                firstLock.unlock();
                secondLock.unlock();
                System.out.println(Thread.currentThread().getName()+" 正常结束!");
            }
        }
    }
    
    /**
     * Conditon机制参考 ReentrantLockBlockingQueueDemo
     */
    static Condition condition = fairLock.newCondition();
    @Test
    public void testCondition() throws InterruptedException {
        fairLock.lock();
        new Thread(new SignalThread()).start();
        System.out.println("主线程等待通知");
        try {
            condition.await();
        } finally {
            fairLock.unlock();
        }
        System.out.println("主线程恢复运行");
    }
    static class SignalThread implements Runnable {
        @Override
        public void run() {
            fairLock.lock();
            try {
                condition.signal();
                System.out.println("子线程通知");
            } finally {
                fairLock.unlock();
            }
        }
    }
}