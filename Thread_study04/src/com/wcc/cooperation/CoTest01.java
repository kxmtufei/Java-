package com.wcc.cooperation;
/**
 * 	协作模型:生产者消费者实现方式一:管程法
 * 	借助缓冲区
 * @author wcc
 * @Date 2019年12月12日 下午11:14:52
 */
public class CoTest01 {
	public static void main(String[] args) {
		SynContainer container = new SynContainer();
		new productor(container).start();
		new Customer(container).start();
	}
}
//生产者
class productor extends Thread{
	SynContainer container;
	public productor(SynContainer container) {
		this.container = container;
	}
	@Override
	public void run() {
		for(int i=1;i<=100;i++) {
			System.out.println("生产->"+ i +"个包子");
			container.push(new BaoZi(i));
		}
	}
	
}
//消费者
class Customer extends Thread{
	SynContainer container;
	public Customer(SynContainer container) {
		this.container = container;
	}
	@Override
	public void run() {
		for(int i=1;i<=100;i++) {
			System.out.println("消费->"+ container.pop().i +"个包子");
		}
	}
}
//缓冲区
class SynContainer{
	BaoZi[] baoZis = new BaoZi[10];//容器
	int count;//计数器
	
	//生产
	public synchronized void push(BaoZi baoZi) {
		//何时能生产  容器存在空间
		//不能生产 只有等待
		if (count == baoZis.length) {
			try {
				this.wait(); //线程阻塞  消费者通知生产解除
			} catch (InterruptedException e) {
			}
		}
		//存在空间 可以生产
		baoZis[count] = baoZi;
		count++;
		//存在数据了，可以通知消费了
		this.notifyAll();
	}
	//消费
	public synchronized BaoZi pop() {
		//何时消费 容器中是否存在数据
		if (count == 0) {
			try {
				this.wait(); //线程阻塞  生产者通知消费解除
			} catch (InterruptedException e) {
			}
		}
		//存在数据可以消费
		count--;
		BaoZi baoZi = baoZis[count];
		this.notifyAll(); //存在空间了，可以唤醒对方生产了
		return baoZi;
			
	}
}
//对象-包子
class BaoZi{
	int i;

	public BaoZi(int i) {
		this.i = i;
	}
	
}