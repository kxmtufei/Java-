package com.wcc.other;
/**
 * 任务调度: Timer 和TimerTask类
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest01 {
	public static void main(String[] args) {
		Timer timer = new Timer();
		//执行计划
//		timer.schedule(new MyTask(), 1000);//延迟1秒后，执行一次
//		timer.schedule(new MyTask(), 1000, 500);//延迟1秒后开始执行，每隔500毫秒，重复一次
		Calendar calendar = new GregorianCalendar(2019,11,14,10,29,00);
		timer.schedule(new MyTask(), calendar.getTime(),200);//指定开始时间
		System.out.println(calendar.getTime());
	}
}
//任务类
class MyTask extends TimerTask{
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("牛逼普拉斯~");
		}
		System.out.println("---------end------------");
	}
}