package com.wcc.syn;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟影院订票--选购指定位置
 * @author wcc
 * @Date 2019年12月11日 下午11:24:47
 */
public class HappyCinema2 {
	public static void main(String[] args) throws InterruptedException {
		//影院可选位置
		List<Integer> available = new ArrayList<>();
		available.add(1);
		available.add(2);
		available.add(3);
		available.add(5);
		available.add(6);
		available.add(7);
		
		//铁蛋选位置
		List<Integer> seats1 = new ArrayList<>();
		seats1.add(1);
		seats1.add(2);
		seats1.add(3);
		//二狗选位置
		List<Integer> seats2 = new ArrayList<>();
		seats2.add(7);
		seats2.add(5);
		seats2.add(2);
		SpringCinema c = new SpringCinema(available, "春天影院");
		new Thread(new HappyCustomer(c,seats1),"铁蛋").start();
//		Thread.sleep(100);
		new Thread(new HappyCustomer(c,seats2),"二狗").start();
	}
}
//顾客
class HappyCustomer implements Runnable{
	SpringCinema cinema;
	List<Integer> seats;
	
	public HappyCustomer(SpringCinema cinema, List<Integer> seats) {
		super();
		this.cinema = cinema;
		this.seats = seats;
	}

	@Override
	public void run() {
		if (cinema.available.size()<=0) {
			System.out.println("座位已售罄！！");
			return;
		}
		synchronized (cinema) {
			boolean flag = cinema.bookTickets(seats);
			if (flag) {
				System.out.println(Thread.currentThread().getName() + "，欲购位置：" + seats + "，出票成功！");
			} else {
				System.out.println(Thread.currentThread().getName() + "，欲购位置：" + seats + "，出票失败，含恨自杀！");
			}
		}
	}
}

//影院
class SpringCinema{
	List<Integer> available; //可选位置编号
	String name;//名称
	public SpringCinema(List<Integer> available, String name) {
		super();
		this.available = available;
		this.name = name;
	}
	
	//购票
	public boolean bookTickets(List<Integer> seats) {
		System.out.println("欢迎光临！ " + name +" ,当前剩余座位：" + available);
		//拷贝一份原始数据，防止直接操做原始数据
		List<Integer> copy = new ArrayList<Integer>();
		copy.addAll(available);
		
		//选购位置，可用位置-指定位置，求差
		copy.removeAll(seats);
		
		//判断大小，可用位-指定位置 ≠ copy，选座失败
		if (available.size()-seats.size() != copy.size()) {
			return false;
		}
		//成功
		available = copy;
		return true;	
	}
}