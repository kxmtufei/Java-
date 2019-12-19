package com.wcc.syn;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程不安全：操作容器
 * @author wcc
 * @Date 2019年12月10日 下午11:48:43
 */
public class UnsafeTest03 {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 40; i++) {
			new Thread(()-> {
				//1、出现null，前一个线程执行完size++，尚未赋值，下一个线程执行了size++，导致上一个线程赋值失败，
				//2、两个线程读取相同的size,后一个值覆盖前一个值，集合没有达到指定的大小
				list.add(Thread.currentThread().getName());//e[30] <-30 失败 [30]<-31成功
			}).start();
		}
		list.forEach(e->System.out.println(e)); 
		System.out.println(list.size());
	}

}
