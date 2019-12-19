package com.wcc.thread;

/**
 *	模拟-龟兔赛跑
 * @author admin
 *
 */
public class Racer implements Runnable {
	private String winner; //胜利者
	public static void main(String[] args) {
		Racer racer = new Racer();
		new Thread(racer,"乌龟").start();
		new Thread(racer,"兔子").start();
	}

	@Override
	public void run() {
		for (int steps = 1; steps <= 100; steps++) {
			//模拟兔子休息
			if ("兔子".equals(Thread.currentThread().getName()) && steps%10==0 ) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + "-->" + steps);
			//比赛是否结束
			boolean flag = gameOver(steps);
			if (flag) {
				break;
			}
		}
		
	}
	
	private boolean gameOver(int steps) {
		if (null != winner) {//存在胜利者
			return true;
		}else {
			if (steps == 100) {//步数达到100
				winner = Thread.currentThread().getName();
				System.out.println("winner -->" + winner );
				return true;
			}
		}
		return false;
	}
}
