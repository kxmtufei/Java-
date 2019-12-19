package com.wcc.thread;

/**
 * Lambda表达式 简化线程(用一次)的使用
 * 
 * @author wcc
 *
 */
public class LambdaThread {
	// 静态内部类
	static class Test implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println("一边吃鸡。。");
			}
		}
	}

	public static void main(String[] args) {
//		new Thread(new Test()).start();

		// 局部内部类
		class Test2 implements Runnable {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("一边吃鸡。。");
				}
			}
		}
		new Thread(new Test2()).start();

		// 匿名内部类 必须借助接口或者父类
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("一边吃鸡。。");
				}
			}
		}).start();

		// jdk8 简化 lambda表达式 只能推导一个方法
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println("一边吃鸡。。");
			}
		}).start();
	}

}
