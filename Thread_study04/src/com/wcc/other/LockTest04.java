package com.wcc.other;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁: 锁可以延续使用 + 计数器
 * 
 * @author wcc
 * @Date 2019年12月14日 下午11:18:46
 */
public class LockTest04 {
	ReentrantLock lock = new ReentrantLock();

	public void a() throws InterruptedException {
		lock.lock();
		System.out.println(lock.getHoldCount());
		System.out.println("进入 a... ");
		doSomething();
		lock.unlock();
		System.out.println(lock.getHoldCount());
		
	}

	// 不可重入
	public void doSomething() throws InterruptedException {
		lock.lock();
		System.out.println(lock.getHoldCount());
		System.out.println("进入 doSomething... ");
		// ...................
		lock.unlock();
		System.out.println(lock.getHoldCount());
	}

	public static void main(String[] args) throws InterruptedException {
		LockTest04 test = new LockTest04();
		test.a();
		Thread.sleep(200);
		System.out.println(test.lock.getHoldCount());
	}
}

