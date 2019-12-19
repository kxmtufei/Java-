package com.wcc.other;

/**
 * ThreadLocal:分析上下文 环境  起点
 * 1、构造器: 哪里调用 就属于哪里 找线程体
 * 2、run方法:本线程自身的
 * @author wcc
 * @Date 2019年12月14日 下午10:04:31
 */
public class ThreadLocalTest03 {
	private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

	public static void main(String[] args) {
			new Thread(new MyRun()).start(); 
	}

	public static class MyRun implements Runnable {
		public MyRun(){
			threadLocal.set(2); //属于main,因为是由main发起，调用new Thread -> new MyRun
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
		}
		public void run() {
			//属于本线程
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
			//new Thread(new MyRunxxx()).start();
		}
	}
}
