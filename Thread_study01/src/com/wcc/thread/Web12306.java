package com.wcc.thread;

/**
 * 	多线程-卖票 ，共享资源，存在并发(线程安全)
 * @author wcc
 *
 */
public class Web12306 implements Runnable{
	//票数
	private int ticketNums = 99; 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) { 
			if (ticketNums<0) {
				break;
			}
			//网络延迟，出现负数票
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "-->" + ticketNums--);
		}
	}

	public static void main(String[] args) {
		//实现runnable接口，可以生成多个代理，共享同一资源
		//一份资源
		Web12306 web = new Web12306();
		new Thread(web, "张学友").start();
		new Thread(web, "刘德华").start();
		new Thread(web, "黎明").start();
	}

}
