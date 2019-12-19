package com.wcc.syn;
/**
 * 线程安全: 在并发时保证数据的正确性、效率尽可能高
 * synchronized
 * 1、同步方法
 * 2、同步块 ,目标更明确
 * @author wcc
 * @Date 2019年12月10日 下午11:44:28
 */
public class SynBlockTest01 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Account account = new Account(160, "结婚礼金");
		SynDrawing my = new SynDrawing(account, 60, "俺");
		SynDrawing xifu = new SynDrawing(account, 100, "俺娘们");
		SynDrawing zml = new SynDrawing(account, 10, "丈母娘");
		my.setPriority(Thread.MAX_PRIORITY);//设置最大优先级-10
		xifu.setPriority(Thread.NORM_PRIORITY);//设置默认优先级-5
		zml.setPriority(Thread.MIN_PRIORITY);//设置最小优先级-1
		xifu.start();
		my.start();
		Thread.sleep(1000);
		zml.start();
	}

}

//模拟取款
class SynDrawing extends Thread{
	Account account;//取钱的账户
	int drawingMoney;//取的钱
	int packetTotal;//口袋的钱
	public SynDrawing(Account account, int drawingMoney, String name) {
		super(name);//设置线程名,父类构造
		this.account = account;
		this.drawingMoney = drawingMoney;
	}
	@Override
	public void run() {
		test();
	}
	//目标锁定account
	public void test() {
		//提高性能
		if (account.money<=0) {
			System.out.println("账户没钱咯..");
			return;
		}
		//同步块
		synchronized (account) {
			if (account.money - drawingMoney < 0) {
				System.out.println("账户不够取..");
				return;
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			account.money -= drawingMoney;
			packetTotal += drawingMoney;
			System.out.println(this.getName() + "-->账户余额为:" + account.money);
			System.out.println(this.getName() + "-->口袋的钱为:" + packetTotal);
		}
	}
}