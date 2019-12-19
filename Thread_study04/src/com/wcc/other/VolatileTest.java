package com.wcc.other;
/**
 * volatile用于保证数据的同步，也就是可见性，防止指令重排，不保证原子性，轻量级的Synchorize
 * 
 * @author 裴新 QQ:3401997271
 *
 */
public class VolatileTest {
	private volatile static int num = 0;
	public static void main(String[] args) throws InterruptedException {
		new Thread(()->{
			while(num==0) { //此处不要编写代码,让CPU不停工作，没时间去读取修改的数据
				
			}
		}) .start();
		
		Thread.sleep(1000);
		num = 1;
	}

}
