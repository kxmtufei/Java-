package com.wcc.thread;
/**
 * 其他方法
 * isAlive:线程是否还活着
 * Thread.currentThread():当前线程
 * setName、getName:代理名称
 * 程序猿的劳斯莱斯
 * @author wcc
 * @Date 2019年12月10日 下午11:07:20
 */
public class InfoTest {
	public static void main(String[] args) throws InterruptedException {
		//设置名称 ：真实角色+代理角色
		MyInfo info = new MyInfo("战斗机");
		Thread t = new Thread(info);
		t.setName("公鸡");
		t.start();
		Thread.sleep(200);
		System.out.println(t.isAlive());
	}
}
class MyInfo implements Runnable{
	private String name;
	
	public MyInfo(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"-->"+name);
	}
}