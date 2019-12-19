package com.wcc.syn;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程不安全：操作容器
 * @author wcc
 * @Date 2019年12月10日 下午11:48:43
 */
public class SynBlockTest02 {

	public static void main(String[] args) throws InterruptedException {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10000; i++) {
			new Thread(()-> {
				//同步块
				synchronized (list) {
					list.add(Thread.currentThread().getName());//e[30] <-30 失败 [30]<-31成功
				}
			}).start();
		}
//		list.forEach(e->System.out.println(e)); 
		//睡眠1秒，保证所有线程都执行完毕
		Thread.sleep(1000);
		System.out.println(list.size());
	}

}
