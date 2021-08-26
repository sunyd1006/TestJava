package com.java.threadLeecode;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

// https://leetcode-cn.com/problems/print-zero-even-odd/solution/java-san-chong-xing-neng-you-yue-de-jie-h4pxx/

/**
 * 要求：打印 010203
 * volatile + ReentrantLock
 */
public class ZeroEvenOdd1116 {
	public static void main(String[] args) {
		ZeroEvenOddReentrantLock demo = new ZeroEvenOddReentrantLock(3);
		new Thread(() -> {
			IntConsumer intConsumer = (i) -> {
				System.out.println(String.format("doubleConsumer receive-->%s", i));
			};
			try {
				demo.zero(intConsumer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		new Thread(() -> {
			IntConsumer intConsumer = (i) -> {
				System.out.println(String.format("doubleConsumer receive-->%s", i));
			};
			try {
				demo.even(intConsumer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		new Thread(() -> {
			IntConsumer intConsumer = (i) -> {
				System.out.println(String.format("doubleConsumer receive-->%s", i));
			};
			try {
				demo.odd(intConsumer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
}

class ZeroEvenOddReentrantLock {
	private int n;
	private volatile int curValue = 0;
	private Lock l = new ReentrantLock();
	private Condition z = l.newCondition();
	private Condition o = l.newCondition();
	private Condition e = l.newCondition();
	
	public ZeroEvenOddReentrantLock(int n) {
		this.n = n;
	}
	
	// printNumber.accept(x) outputs "x", where x is an integer.
	public void zero(IntConsumer printNumber) throws InterruptedException {
		l.lock();
		try {
			for (int i = 1; i <= n; i++) {
				if (curValue != 0) {
					z.await();
				}
				printNumber.accept(0);
				if (i % 2 == 1) {
					curValue = 1;
					o.signal();
				} else {
					curValue = 2;
					e.signal();
				}
			}
		} finally {
			l.unlock();
		}
	}
	
	public void even(IntConsumer printNumber) throws InterruptedException {
		l.lock();
		try {
			for (int i = 2; i <= n; i += 2) {
				if (curValue != 2) {
					e.await();
				}
				printNumber.accept(i);
				curValue = 0;
				z.signal();
			}
		} finally {
			l.unlock();
		}
	}
	
	public void odd(IntConsumer printNumber) throws InterruptedException {
		l.lock();
		try {
			for (int i = 1; i <= n; i += 2) {
				if (curValue != 1) {
					o.await();
				}
				printNumber.accept(i);
				curValue = 0;
				z.signal();
			}
		} finally {
			l.unlock();
		}
	}
}

class ZeroEvenOddSamphore {
	private int n;
	private Semaphore z, e, o;
	
	public ZeroEvenOddSamphore(int n) {
		this.n = n;
	}
	
	// printNumber.accept(x) outputs "x", where x is an integer.
	public void zero(IntConsumer printNumber) throws InterruptedException {
	
	}
	
	public void even(IntConsumer printNumber) throws InterruptedException {
	
	}
	
	public void odd(IntConsumer printNumber) throws InterruptedException {

	}
}
