 package com.wcc.thread;

/**
 * 创建线程方式一： 1、创建：实现Runnable+重写run 2、启动：创建子类对象，调用start
 * 
 * @author wcc
 *
 */
public class StartRun implements Runnable {
	/**
	 * 线程入口点
	 */
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("一边吃鸡。。");
		}
	}

	public static void main(String[] args) {
		// 创建实现类对象
//		StartRun sr = new StartRun();
		//创建代理类对象
//		Thread t = new Thread(sr);
		//启动
//		t.start();
		
		//对象只使用一次，可以使用匿名
		new Thread(new StartRun()).start();
		for (int i = 0; i < 10; i++) {
			System.out.println("一边喝排骨汤");
		}
	}

}
