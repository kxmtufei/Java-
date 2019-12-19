package com.wcc.syn;
/**
 * 模拟12306--选购票数（使用同步方法）
 * @author wcc
 * @Date 2019年12月11日 下午11:24:47
 */
public class Happy12306 {
	public static void main(String[] args) throws InterruptedException {
		Cinema c = new Cinema(3, "厦门火车站");
		new Thread(new Customer(c,3),"铁蛋").start();
//		Thread.sleep(100);
		new Thread(new Customer(c,2),"二狗").start();
	}
}
//乘客
class Passenger extends Thread{
	int seats;
	
	public Passenger(Runnable target, String name, int seats) {
		super(target,name);
		this.seats = seats;
	}

}

//12306厦门站
class Web12306 implements Runnable{
	int available; //可用的位置
	String name;//名称
	public Web12306(int available, String name) {
		super();
		this.available = available;
		this.name = name;
	}
	
	@Override
	public void run() {
		if (available<=0) {
			System.out.println("座位已售罄！！");
			return;
		}
		Passenger p = (Passenger)Thread.currentThread();//强转为子类 
		boolean flag = this.bookTickets(p.seats);
		if (flag) {
			System.out.println(Thread.currentThread().getName() + "，欲购票数：" + p.seats + "，出票成功！");
		} else {
			System.out.println(Thread.currentThread().getName() + "，欲购票数：" + p.seats + "，出票失败，含恨自杀！");
		}
	}
	
	//购票
	public synchronized boolean bookTickets(int seats) {
		System.out.println("欢迎光临！ " + name +" ,当前剩余座位数：" + available);
		if (seats>available) {
			return false;
		}
		available -= seats;
		return true;	
	}
}