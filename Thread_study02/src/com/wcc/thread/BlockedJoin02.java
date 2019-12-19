package com.wcc.thread;

public class BlockedJoin02 {
	public static void main(String[] args) {
		new Thread(new Father()).start();
	}
}
class Father implements Runnable{
	@Override
	public void run() {
		System.out.println("老爸突然想抽烟，于是让儿子去买中华...");
		Thread son = new Thread(new Son());
		son.start();
		try {
			son.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("儿子给王阿姨拐走了。。。");
		}
		System.out.println("老爸接过烟，一看不是中华，直接把儿子腿打断了...");
	}
}

class Son implements Runnable{
	@Override
	public void run() {
		System.out.println("儿子接过钱，兴冲冲地出门了...");
		System.out.println("路上遇到了隔壁王阿姨，和阿姨聊了10秒的人生...");
		for(int i=1;i<=10;i++) {
			System.out.println(i + "秒过去了..");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("赶紧去买烟了...");
		System.out.println("拿着一把红花大金元回家了...");
		
	}
}