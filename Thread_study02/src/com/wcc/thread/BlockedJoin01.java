package com.wcc.thread;
/**
 * 	join:合并线程，插队线程
 * @author wcc
 * @Date 2019年12月9日 下午11:31:13
 */
public class BlockedJoin01 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread t = new Thread(()-> {
			for (int i = 0; i < 100; i++) {
				System.out.println("lambda.." + i);
			}
		});
		t.start();
		
		for (int i = 0; i < 100; i++) {
			if (i==20) {
				t.join();//插队 main被组设了 可设置时长，超过时长不在阻塞其他线程
			}
			System.out.println("main.." + i);
		}
	}

}
