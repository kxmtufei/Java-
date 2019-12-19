package com.wcc.other;

/**
 * 不可重入锁: 锁不可以延续使用
 * 
 * @author wcc
 * @Date 2019年12月14日 下午11:18:46
 */
public class LockTest02 {
	Lock lock = new Lock();

	public void a() throws InterruptedException {
		lock.lock();
		System.out.println("进入 a... ");
		doSomething();
		lock.unLock();

	}

	// 不可重入
	public void doSomething() throws InterruptedException {
		lock.lock();
		System.out.println("进入 doSomething... ");
		// ...................
		lock.unLock();
	}

	public static void main(String[] args) throws InterruptedException {
		LockTest02 test = new LockTest02();
		test.a();

	}
}

//不可重入锁
class Lock {
	// 是否占用
	private boolean isLocked = false;

	// 使用锁
	public synchronized void lock() throws InterruptedException {
		// 已被锁定
		while (isLocked) {
			wait();
		}
		// 未被锁定
		isLocked = true;
	}

	// 释放锁
	public synchronized void unLock() {
		isLocked = false;
		notify();
	}
}
