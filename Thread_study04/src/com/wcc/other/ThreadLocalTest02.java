package com.wcc.other;

/**
 * ThreadLocal:每个线程自身的数据，更改不会影响其他线程，发爱疯，5个组，每组发一台
 * @author wcc
 * @Date 2019年12月14日 下午10:04:31
 */
public class ThreadLocalTest02 {
	private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

	public static void main(String[] args) {
		for (int i = 1; i <= 5; i++) {
			new Thread(new MyRun(),i+"组").start(); 
		}
		
	}

	public static class MyRun implements Runnable {
		public void run() {
			Integer count = threadLocal.get();
			System.out.println(Thread.currentThread().getName() + "得到爱疯-->" + count);
			threadLocal.set(count - 1);
			System.out.println(Thread.currentThread().getName() + "发出一台爱疯，还剩下-->" + threadLocal.get());
		}
	}
}
