package com.wcc.other;
/**
 * ThreadLocal:每个线程自身的存储本地、局部区域
 *  get/set/initialValue
 * @author wcc
 * @Date 2019年12月14日 下午10:04:31
 */
public class ThreadLocalTest01 {
//	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
	//更改初始化值
/*	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 2;
		}; 
	};*/
	private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()-> 200);
	public static void main(String[] args) {
		//获取值
		System.out.println(Thread.currentThread().getName() + "->" + threadLocal.get());
		//设值
		threadLocal.set(2);
		System.out.println(Thread.currentThread().getName() + "->" + threadLocal.get());
		new Thread(new MyRun()).start();
		new Thread(new MyRun()).start();
	}
	public static  class MyRun implements Runnable{
		public void run() {
			threadLocal.set((int)(Math.random()*99));
			System.out.println(Thread.currentThread().getName()+"-->"+threadLocal.get());		
		}
	}
}


