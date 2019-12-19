package com.wcc.syn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 线程安全：操作并发容器
 * 
 * @author wcc
 * @Date 2019年12月12日 上午12:09:25
 */
public class SynContainer {
	public static void main(String[] args) throws InterruptedException {
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
		for (int i = 0; i < 10000; i++) {
			new Thread(() -> {
				list.add(Thread.currentThread().getName());// e[30] <-30 失败 [30]<-31成功
			}).start();
		}
//		list.forEach(e->System.out.println(e)); 
		// 睡眠1秒，保证所有线程都执行完毕
		Thread.sleep(1000);
		System.out.println(list.size());
	}
}
