package com.wcc.syn;
/**
 * 线程安全: 在并发时保证数据的正确性、效率尽可能高
 * synchronized
 * 1、同步方法
 * 2、同步块 
 * @author wcc
 * @Date 2019年12月10日 下午11:44:28
 */
public class SynTest02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account account = new Account(100, "结婚礼金");
		Safedrawing my = new Safedrawing(account, 60, "俺");
		Safedrawing xifu = new Safedrawing(account, 70, "俺娘们");
		my.start();
		xifu.start();
	}

}

//模拟取款
class Safedrawing extends Thread{
	Account account;//取钱的账户
	int drawingMoney;//取的钱
	int packetTotal;//口袋的钱
	public Safedrawing(Account account, int drawingMoney, String name) {
		super(name);//设置线程名,父类构造
		this.account = account;
		this.drawingMoney = drawingMoney;
	}
	@Override
	public void run() {
		test();
	}
	//锁的应该是共享资源-Account，而不是Safedrawing，所以同步方法无法保证达到线程安全
	public synchronized void test() {
		if(account.money -drawingMoney<0) {
			return; 
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		account.money-=drawingMoney;
		packetTotal+=drawingMoney;
		System.out.println(this.getName()+"-->账户余额为:"+account.money);
		System.out.println(this.getName()+"-->口袋的钱为:"+packetTotal);
	}
}