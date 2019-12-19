package com.wcc.thread;
/**
 * 	yield 礼让线程，暂停线程 直接进入就绪状态不是阻塞状态
 * @author wcc
 * @Date 2019年12月9日 下午11:00:00
 */
public class YieldDemo01 {

	public static void main(String[] args) {
		MyYield my = new MyYield();
		new Thread(my,"a").start();
		new Thread(my,"b").start();
	}

}

class MyYield implements Runnable{
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "..start");
		Thread.yield();
		System.out.println(Thread.currentThread().getName() + "..end");
	}
}