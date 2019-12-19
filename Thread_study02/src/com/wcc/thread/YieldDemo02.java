package com.wcc.thread;
/**
 * 	yield 礼让线程，暂停线程 直接进入就绪状态不是阻塞状态
 * @author wcc
 * @Date 2019年12月9日 下午11:00:00
 */
public class YieldDemo02 {

	public static void main(String[] args) {
		new Thread(()-> {
			for (int i = 0; i < 100; i++) {
				System.out.println("lambda.."+i);
			}
		}).start();
		for (int i = 0; i < 100; i++) {
			if (i%20==0) {
				Thread.yield();
			}
			System.out.println("main->"+i);
		}
	}

}
