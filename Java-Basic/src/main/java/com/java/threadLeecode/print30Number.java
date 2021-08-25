package com.java.threadLeecode;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// https://leetcode-cn.com/problems/print-foobar-alternately/solution/duo-xian-cheng-liu-mai-shen-jian-ni-xue-d220n/
// BlockingQueue
public class print30Number {
	public static void main(String[] args) throws InterruptedException {
		Resource30 fooBar = new Resource30(30);
		Thread first = new Thread(() -> {
			try {
				fooBar.printA();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		Thread second = new Thread(() -> {
			try {
				fooBar.printB();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		Thread three = new Thread(() -> {
			try {
				fooBar.printC();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		Thread four = new Thread(() -> {
			try {
				fooBar.printD();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		first.start();
		second.start();
		three.start();
		four.start();
		
		first.join();
		second.join();
		three.join();
		four.join();
		
		System.out.println(Thread.currentThread().getName());
	}
}

class Resource30 {
	private int start;
	private Object mutex1;
	private Object mutex2;
	private int n;
	private BlockingQueue<Integer> bar = new LinkedBlockingQueue<>(1);
	private BlockingQueue<Integer> foo = new LinkedBlockingQueue<>(1);
	
	public Resource30(int n) {
		mutex1 = new Object();
		this.start = 0;
		this.n = n;
	}
	
	public void printA() throws InterruptedException {
		while(start < n){
			synchronized (mutex1){
				if((start & 1) ==1){
					System.out.println(Thread.currentThread().getName() + ": " + start);
					start++;
					if(mutex2==null){mutex2=new Object();} mutex2.notify();
				}else{
					mutex1.wait();
				}
			}
		}
	}
	
	public void printB() throws InterruptedException {
		while(start < n){
			synchronized (mutex1){
				if((start & 1) ==1){
					System.out.println(Thread.currentThread().getName() + ": " + start);
					start++;
					if(mutex2==null){mutex2=new Object();} mutex2.notify();
				}else{
					mutex1.wait();
				}
			}
		}
	}
	
	public void printC() throws InterruptedException {
		while(start < n){
			synchronized (mutex1){
				if((start & 1) ==1){
					System.out.println(Thread.currentThread().getName() + ": " + start);
					start++;
					if(mutex2==null){mutex2=new Object();} mutex2.notify();
				}else{
					mutex1.wait();
				}
			}
		}
	}
	
	public void printD() throws InterruptedException {
		while(start < n){
			synchronized (mutex1){
				if( (start & 1) == 0){
					if(mutex2==null) mutex2 = new Object();
					synchronized (mutex2){
						System.out.println(Thread.currentThread().getName() + ": " + start);
						start++;
					}
				}else {
					// 奇数
					System.out.println(Thread.currentThread().getName() + ": " + start);
					start++;
				}
			}
		}
	}
}
