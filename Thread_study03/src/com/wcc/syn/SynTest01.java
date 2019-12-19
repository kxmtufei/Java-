package com.wcc.syn;

/**
 * 线程安全: 在并发时保证数据的正确性、效率尽可能高
 * synchronized
 * 1、同步方法
 * 2、同步块
 * @author wcc
 * @Date 2019年12月10日 下午11:25:41
 */
public class SynTest01 {
	public static void main(String[] args) {
		// 一份资源
		SafeWeb12306 web = new SafeWeb12306();
		new Thread(web, "张学友").start();
		new Thread(web, "刘德华").start();
		new Thread(web, "黎明").start();
	}
}

class SafeWeb12306 implements Runnable {
	// 票数
	private int ticketNums = 10;
	private boolean flag = true;

	@Override
	public void run() {
		// 网络延迟
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (flag) {
			test();
		}
	}
	//线程安全  同步 (锁的是对象的资源，这里指的就是this)
	private synchronized void test() {
		if (ticketNums < 0) {
			flag = false;
			return;
		}
		// 网络延迟，出现负数票
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
	}
}