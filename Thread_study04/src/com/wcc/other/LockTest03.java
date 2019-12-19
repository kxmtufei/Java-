package com.wcc.other;

/**
 * 可重入锁: 锁可以延续使用 + 计数器
 * 
 * @author wcc
 * @Date 2019年12月14日 下午11:18:46
 */
public class LockTest03 {
	ReLock lock = new ReLock();

	public void a() throws InterruptedException {
		lock.lock();
		System.out.println(lock.getHoldCount());
		System.out.println("进入 a... ");
		doSomething();
		lock.unLock();
		System.out.println(lock.getHoldCount());
		
	}

	//可重入
	public void doSomething() throws InterruptedException {
		lock.lock();
		System.out.println(lock.getHoldCount());
		System.out.println("进入 doSomething... ");
		// ...................
		lock.unLock();
		System.out.println(lock.getHoldCount());
	}

	public static void main(String[] args) throws InterruptedException {
		LockTest03 test = new LockTest03();
		test.a();
		Thread.sleep(200);
		System.out.println(test.lock.getHoldCount());
	}
}
//可重入锁
class ReLock {
	//是否占用
	private boolean isLocked = false;
	private Thread lockedBy = null; //存储线程
	//计数器
	private int holdCount = 0;
	public int getHoldCount() {
		return holdCount;
	}
	//使用锁
	public synchronized void lock() throws InterruptedException {
		Thread t = Thread.currentThread();
		// 已被锁定
		while (isLocked && lockedBy!=t) {
			wait();
		}
		// 未被锁定，加锁
		isLocked = true;
		lockedBy = t;
		holdCount++;
	}
	//释放锁
	public synchronized void unLock() {
		if (Thread.currentThread() == lockedBy) {
			holdCount--;
			if (holdCount == 0) {
				isLocked = false;
				notify();
				lockedBy = null;
			}
		}
	}
}