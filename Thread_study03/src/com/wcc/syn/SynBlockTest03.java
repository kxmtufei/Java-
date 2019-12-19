package com.wcc.syn;

/**
 * 线程安全: 在并发时保证数据的正确性、效率尽可能高 synchronized 1、同步方法 2、同步块
 * 
 * @author wcc
 * @Date 2019年12月10日 下午11:25:41
 */
public class SynBlockTest03 {
	public static void main(String[] args) {
		// 一份资源
		SynWeb12306 web = new SynWeb12306();
		new Thread(web, "张学友").start();
		new Thread(web, "刘德华").start();
		new Thread(web, "黎明").start();
	}
}

class SynWeb12306 implements Runnable {
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
			test5();
		}
	}

	// 线程安全 同步 (锁的是对象的资源，这里指的就是this)
	private synchronized void test1() {
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

	// 线程安全 范围太大 -->效率低下
	private void test2() {
		synchronized (this) {
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

	// 线程不安全 ticketNums对象在变 ,锁的应该是(地址)不变的对象，如this,class模板
	private void test3() {
		synchronized ((Integer) ticketNums) {
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
	
	//线程不安全  范围太小锁不住
	private void test4() {
		synchronized (this) {
			if (ticketNums < 0) {
				flag = false;
				return;
			}
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
	
	//线程安全:尽可能锁定合理的范围(不是指代码 指数据的完整性)
		//double checking 双重检测
		private void test5() {
			if (ticketNums < 0) {//考虑的是没有票的情况下，直接退出，不再去观察对象是否处于锁定状态，提高性能
				flag = false;
				return;
			}
			synchronized (this) {
				if (ticketNums < 0) {//考虑最后的1张票（临界值）
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
}