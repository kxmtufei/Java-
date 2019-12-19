package com.wcc.syn;
/**
 * 模拟影院订票--选购票数
 * @author wcc
 * @Date 2019年12月11日 下午11:24:47
 */
public class HappyCinema {
	public static void main(String[] args) throws InterruptedException {
		Cinema c = new Cinema(3, "春天影院");
		new Thread(new Customer(c,3),"铁蛋").start();
//		Thread.sleep(100);
		new Thread(new Customer(c,2),"二狗").start();
	}
}
//顾客
class Customer implements Runnable{
	Cinema cinema;
	int seats;
	
	public Customer(Cinema cinema, int seats) {
		super();
		this.cinema = cinema;
		this.seats = seats;
	}

	@Override
	public void run() {
		if (cinema.available<=0) {
			System.out.println("座位已售罄！！");
			return;
		}
		synchronized (cinema) {
			boolean flag = cinema.bookTickets(seats);
			if (flag) {
				System.out.println(Thread.currentThread().getName() + "，欲购票数：" + seats + "，出票成功！");
			} else {
				System.out.println(Thread.currentThread().getName() + "，欲购票数：" + seats + "，出票失败，含恨自杀！");
			}
		}
	}
}

//影院
class Cinema{
	int available; //可用的位置
	String name;//名称
	public Cinema(int available, String name) {
		super();
		this.available = available;
		this.name = name;
	}
	
	//购票
	public boolean bookTickets(int seats) {
		System.out.println("欢迎光临！ " + name +" ,当前剩余座位数：" + available);
		if (seats>available) {
			return false;
		}
		available -= seats;
		return true;	
	}
}