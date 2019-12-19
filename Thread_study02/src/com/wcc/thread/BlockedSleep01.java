package com.wcc.thread;
/**
 * sleep模拟网络延时，放大了发生问题的可能性(并发安全)
 * @author wcc
 * @Date 2019年12月9日 下午10:42:29
 */
public class BlockedSleep01 {
	public static void main(String[] args) {
		//实现runnable接口，可以生成多个代理，共享同一资源
		//一份资源
		Web12306 web = new Web12306();
		new Thread(web, "张学友").start();
		new Thread(web, "刘德华").start();
		new Thread(web, "黎明").start();
	}
}
class Web12306 implements Runnable{
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
}