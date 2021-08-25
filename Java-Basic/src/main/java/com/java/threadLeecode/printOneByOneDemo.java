package com.java.threadLeecode;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// https://leetcode-cn.com/problems/print-foobar-alternately/solution/duo-xian-cheng-liu-mai-shen-jian-ni-xue-d220n/
// BlockingQueue
public class printOneByOneDemo {
	public static void main(String[] args) throws InterruptedException {
		FooBar fooBar = new FooBar(3);
		Thread first = new Thread(() -> {
			try {
				fooBar.foo(() -> {
					System.out.println("线程名 " + Thread.currentThread().getName() + " foo");
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		Thread second = new Thread(() -> {
			try {
				fooBar.bar(() -> {
					System.out.println("线程名 " + Thread.currentThread().getName() + " bar");
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		first.start();
		second.start();
		first.join();
		second.join();
		System.out.println(Thread.currentThread().getName());
	}
}

class FooBar {
	private int n;
	private BlockingQueue<Integer> bar = new LinkedBlockingQueue<>(1);
	private BlockingQueue<Integer> foo = new LinkedBlockingQueue<>(1);
	
	public FooBar(int n) {
		this.n = n;
	}
	
	public void foo(Runnable printFoo) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			foo.put(i);
			printFoo.run();
			bar.put(i);
		}
	}
	
	public void bar(Runnable printBar) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			bar.take();
			printBar.run();
			foo.take();
		}
	}
}

// CyclicBarrier 控制先后
class FooBar6 {
	private int n;
	
	public FooBar6(int n) {
		this.n = n;
	}
	
	CyclicBarrier cb = new CyclicBarrier(2);
	volatile boolean fin = true;
	
	public void foo(Runnable printFoo) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			while (!fin) ;
			printFoo.run();
			fin = false;
			try {
				cb.await();
			} catch (BrokenBarrierException e) {
			}
		}
	}
	
	public void bar(Runnable printBar) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			try {
				cb.await();
			} catch (BrokenBarrierException e) {
			}
			printBar.run();
			fin = true;
		}
	}
}

//  自旋 + 让出CPU
class FooBar5 {
	private int n;
	
	public FooBar5(int n) {
		this.n = n;
	}
	
	volatile boolean permitFoo = true;
	
	public void foo(Runnable printFoo) throws InterruptedException {
		for (int i = 0; i < n; ) {
			if (permitFoo) {
				printFoo.run();
				i++;
				permitFoo = false;
			} else {
				Thread.yield();
			}
		}
	}
	
	public void bar(Runnable printBar) throws InterruptedException {
		for (int i = 0; i < n; ) {
			if (!permitFoo) {
				printBar.run();
				i++;
				permitFoo = true;
			} else {
				Thread.yield();
			}
		}
	}
}


//  volatile标志位 + 可重入锁 + Condition
class FooBar4 {
	private int n;
	
	public FooBar4(int n) {
		this.n = n;
	}
	
	Lock lock = new ReentrantLock(true);
	private final Condition foo = lock.newCondition();
	volatile boolean flag = true;
	
	public void foo(Runnable printFoo) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			lock.lock();
			try {
				while (!flag) {
					foo.await();
				}
				printFoo.run();
				flag = false;
				foo.signal();
			} finally {
				lock.unlock();
			}
		}
	}
	
	public void bar(Runnable printBar) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			lock.lock();
			try {
				while (flag) {
					foo.await();
				}
				printBar.run();
				flag = true;
				foo.signal();
			} finally {
				lock.unlock();
			}
		}
	}
}

// synchronized + volatile标志位 + 唤醒
class FooBar3 {
	private int n;
	// 标志位，控制执行顺序，true执行printFoo，false执行printBar
	private volatile boolean type = true;
	private final Object foo = new Object(); // 锁标志
	
	public FooBar3(int n) {
		this.n = n;
	}
	
	public void foo(Runnable printFoo) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			synchronized (foo) {
				while (!type) {
					foo.wait();
				}
				printFoo.run();
				type = false;
				foo.notifyAll();
			}
		}
	}
	
	public void bar(Runnable printBar) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			synchronized (foo) {
				while (type) {
					foo.wait();
				}
				printBar.run();
				type = true;
				foo.notifyAll();
			}
		}
	}
}


// 信号量 适合控制顺序
class FooBar2 {
	private int n;
	private Semaphore foo = new Semaphore(1);
	private Semaphore bar = new Semaphore(0);
	
	public FooBar2(int n) {
		this.n = n;
	}
	
	public void foo(Runnable printFoo) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			foo.acquire();
			printFoo.run();
			bar.release();
		}
	}
	
	public void bar(Runnable printBar) throws InterruptedException {
		for (int i = 0; i < n; i++) {
			bar.acquire();
			printBar.run();
			foo.release();
		}
	}
}