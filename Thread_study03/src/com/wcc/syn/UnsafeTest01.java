package com.wcc.syn;
/**
 * 线程不安全: 数据有负数、相同
 * @author wcc
 * @Date 2019年12月10日 下午11:25:41
 */
public class UnsafeTest01 {
	public static void main(String[] args) {
		//一份资源
		UnsafeWeb12306 web = new UnsafeWeb12306();
		new Thread(web, "张学友").start();
		new Thread(web, "刘德华").start();
		new Thread(web, "黎明").start();
	}
}
class UnsafeWeb12306 implements Runnable{
	//票数
	private int ticketNums = 10; 
	private boolean flag = true;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (flag) { 
			test();
		}
	}	
	
	private void test() {
		if (ticketNums<0) {
			flag = false;
			return;
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