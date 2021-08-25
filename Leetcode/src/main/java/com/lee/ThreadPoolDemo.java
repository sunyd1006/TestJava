package com.ssp;

// todo 手写线程池
import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ThreadPoolDemo {
	int coreNum;
	int capacity;
	Queue<Integer> taskList;
	List<Thread> threadList;

	public ThreadPoolDemo(int coreNum, int capacity) {
		this.coreNum = coreNum;
		this.capacity = capacity;
		this.taskList = new LinkedList<>();
		this.threadList = new LinkedList<>();
	}

	public void start() {
		if ((this.threadList.size() < this.coreNum)) {
			threadList.add(new MyThread());
		}
	}

	boolean isStop = false;
	joinAll(){

		while(taskList.size()!= 0){}

		forall thread:
			new interuputed()
	}

	public void stop(){
		// 等待任务执行完毕
		// joinAll()

		// stop all thread
		for (int i = 0; i < threadList; i++) {

		}
	}

	public synchronized boolean addTask(Integer i) {
		if(isStop) {
			throw new RuntimeException("NotAllowedToAddTask");
		}

	while (isStop.wait()) {

	}

		if (taskList.size() > capacity){
			// 拒绝策略
			// throw new RuntimeException("TaskListOverflow");
			return false;
		}
		taskList.add(i);
		return true;
	}

	private class MyThread extends Thread {
		@Override
		public void run() {
			while (true && !interrupted()){
				Integer task = getTask();
				if(task != null) {
					System.out.println();
				}else{
					try{
						Thread.sleep(3000);
					}catch (Exception e){
						e.printStackTrace();
					}
					System.out.println("没任务了");
				}
			}
		}
	}

	public synchronized Integer getTask() {
		if (taskList.size() == 0) return null;
		return taskList.poll();
	}

	public static void main(String[] args) {
		ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo(5, 10);

		threadPoolDemo.start();
		// addTask()
		for (int i = 0; i < 10; i++) {
			threadPoolDemo.addTask(i);
		}
	}
}
