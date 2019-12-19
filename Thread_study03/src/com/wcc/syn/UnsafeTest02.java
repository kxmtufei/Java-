package com.wcc.syn;
/**
 * 线程不安全：取钱
 * @author wcc
 * @Date 2019年12月10日 下午11:44:28
 */
public class UnsafeTest02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account account = new Account(100, "结婚礼金");
		Drawing my = new Drawing(account, 60, "俺");
		Drawing xifu = new Drawing(account, 70, "俺娘们");
		my.start();
		xifu.start();
	}

}



class Drawing extends Thread{
	Account account;//取钱的账户
	int drawingMoney;//取的钱
	int packetTotal;//口袋的钱
	public Drawing(Account account, int drawingMoney, String name) {
		super(name);//设置线程名,父类构造
		this.account = account;
		this.drawingMoney = drawingMoney;
	}
	@Override
	public void run() {
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