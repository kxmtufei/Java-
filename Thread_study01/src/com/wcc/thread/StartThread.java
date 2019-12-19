package com.wcc.thread;

/**
 * 创建线程方式一： 1、创建：继承Thread+重写run 2、启动：创建子类对象，调用start
 * 
 * @author wcc
 *
 */
public class StartThread extends Thread {
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
		// 启动
		StartThread startThread = new StartThread();
		startThread.start();
		for (int i = 0; i < 10; i++) {
			System.out.println("一边喝排骨汤");
		}
	}

}
