package com.wcc.thread;

/**
 * lambda推导
 * 
 * @author wcc
 *
 */
public class LambdaTest04 {

	public static void main(String[] args) {

		// lambda 推导必须存在类型

		new Thread(() -> {
			for (int i = 0; i <30; i++) {
				System.out.println("一边喝可乐");
			}
		}).start();
		new Thread(() -> System.out.println("一边喝奶茶。。")).start();

	}
}
