package com.wcc.syn;
/**
 *  	死锁: 过多的同步可能造成相互不释放资源
 *	 从而相互等待，一般发生于同步中持有多个对象的锁
 * 
 * 	避免: 不要在同一个代码块中，同时持有多个对象的锁
 * 
 * @author wcc
 * @Date 2019年12月12日 下午10:17:54
 */
public class DeadLock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new DaYe(1,"红狗").start();
		new DaYe(0,"蓝狼").start();
	}

}

//蓝Buff
class BlueBuff{
	
}
//红Buff
class RedBuff{
	
}

//刷野
class DaYe extends Thread{
	static BlueBuff blue = new BlueBuff();
	static RedBuff red = new RedBuff();
	//选择
	int choice;
	//选手
	String name;
	public DaYe(int choice, String name) {
		super();
		this.choice = choice;
		this.name = name;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		shuaYe();
	}
	//相互持有对方的对象锁(锁中锁)-->可能造成死锁
	private void shuaYe() {
		if (choice == 0) {
			synchronized (blue) {
				System.out.println(this.name + "正在打蓝Buff");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			System.out.println(this.name + "打完蓝Buff，准备抢红Buff");
			synchronized (red) {
				System.out.println(this.name + "成功抢到红Buff");
			}
			
		}else {
			synchronized (red) {
				System.out.println(this.name + "正在打红Buff");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			System.out.println(this.name + "打完红Buff，准备抢蓝Buff");
			synchronized (blue) {
				System.out.println(this.name + "成功抢到蓝Buff");
			}
		}
	}
	
}