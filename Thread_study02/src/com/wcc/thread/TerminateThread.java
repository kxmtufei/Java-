package com.wcc.thread;

import java.util.jar.Attributes.Name;

/**
 * 	终止线程
 * 	1、 线程正常执行完毕->一次
 * 	2、 外部干涉->加入标识
 * @author wcc
 *
 */
public class TerminateThread implements Runnable {
	//1、加入标识，标记线程是否可以运行
	private boolean flag = true;
	private String name;
	
	public TerminateThread(String name) {
		super();
		this.name = name;
	}
	@Override
	public void run() {
		int i=0;
		//2、关联标识，true-->运行 false -->停止
		while (flag) {
			System.out.println(name + "->" + i++);
		}
	}
	//3、对外提供方法改变标识
	private void terminate() {
		this.flag = false;
	}
	public static void main(String[] args) {
		TerminateThread tt = new TerminateThread("铁蛋");
		new Thread(tt).start();
		for(int i=0;i<100;i++) {
			if (i == 88) {
				tt.terminate();
				System.out.println("铁蛋 挂了！");
			}
			System.out.println("main->"+i);
		}
	}
}
