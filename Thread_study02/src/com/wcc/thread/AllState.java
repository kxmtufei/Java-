package com.wcc.thread;

import java.lang.Thread.State;

/**
 * 观察线程的状态 1、NEW 新生 2、RUNNABLE 运行（包括就绪） 
 * 3、Blocked阻塞、 TIMED_WAITING、 等待 4、TERMINATED 死亡
 * @author wcc
 * @Date 2019年12月10日 下午10:13:10
 */
public class AllState {

	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("线程冲冲...");
			}
		});
		State state = t.getState();// NEW
		System.out.println(state);

		t.start();
		state = t.getState();
		System.out.println(state);// RUNNABLE
		/*
		while(state != Thread.State.TERMINATED) {
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			state = t.getState();
			System.out.println(state);// TIMED_WAITING 有时间等待
		}
		state = t.getState();
		System.out.println(state);// TERMINATED 死亡
		*/
		while(true) {
			//活动的线程数
			int num = Thread.activeCount();
			System.out.println(num);
				if (num == 1) {
					break;
				}
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				state = t.getState();
				System.out.println(state);// TIMED_WAITING 有时间等待
			}
	}

}
