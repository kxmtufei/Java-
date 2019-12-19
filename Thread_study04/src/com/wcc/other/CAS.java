package com.wcc.other;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS:比较并交换
 * @author wcc
 * @Date 2019年12月16日 下午10:37:23
 */
public class CAS {
	//库存
	private static AtomicInteger stock = new AtomicInteger(15);
	public static void main(String[] args) {
		for(int i=0;i<15;i++) {
			new Thread(()-> {
				
				int left = stock.decrementAndGet();
				System.out.println(Thread.currentThread().getName() + "抢到了一件"+"-->还剩"+left);
				if (left<1) {
					System.out.println("抢完了...");
					return;
				}
			}).start();
		}
	}
}
