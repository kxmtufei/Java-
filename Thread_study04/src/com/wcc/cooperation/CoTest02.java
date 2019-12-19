package com.wcc.cooperation;import javax.sound.midi.VoiceStatus;

/**
 * 	协作模型:生产者消费者实现方式一:信号灯法
 * 	借助标志位
 * @author wcc
 * @Date 2019年12月12日 下午11:14:52
 */
public class CoTest02 {
	public static void main(String[] args) {
		Tv tv = new Tv();
		new Player(tv).start();
		new Watcher(tv).start();
	}
}
//演员
class Player extends Thread{
	Tv tv;
	
	public Player(Tv tv) {
		this.tv = tv;
	}

	@Override
	public void run() {
		for(int i=0;i<20;i++) {
			if (i%2==0) {
				tv.player("仙剑奇侠传");
			}else {
				tv.player("插播广告，刘辉地黄丸，认准九芝堂，噢耶~~~");
			}
		}
	}
}
//观众
class Watcher extends Thread{
	Tv tv;
	public Watcher(Tv tv) {
		this.tv = tv;
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++) {
			tv.watch();
		}
	}
}
//同一个资源 电视
class Tv{
	String screen;
	//信号灯
	//T 表示演员表演 观众等待
	//F 表示观众观看 演员等待
	boolean flag = true;
	public synchronized void player(String screen) {
		//等待
		if (!flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//表演
		System.out.println("正在表演：" + screen);
		this.screen = screen;
		//唤醒，可以收看了
		this.notifyAll();
		//切换标志,取反
		this.flag = !this.flag;
	}
	
	public synchronized void watch() {
		//等待
		if (flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//观看
		System.out.println("正在收听：" + screen);
		//唤醒，可以收看了
		this.notifyAll();
		//切换标志
		this.flag = !this.flag;
	}
	
}