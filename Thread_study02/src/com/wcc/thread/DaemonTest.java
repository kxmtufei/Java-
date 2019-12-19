package com.wcc.thread;
/**
 * 守护线程：是为用户线程服务的；jvm停止不用等待守护线程执行完毕
 * 默认:用户线程 jvm等待用户线程执行完毕才会停止
 * @author wcc
 * @Date 2019年12月10日 下午10:51:59
 */
public class DaemonTest {

	public static void main(String[] args) {
		You you = new You();
		God god = new God();
		Thread t = new Thread(god);
		t.setDaemon(true);
		t.start();
		new Thread(you).start();
	}

}
class You implements Runnable{
	@Override
	public void run() {
	for(int i=0;i<365*100;i++) {
		System.out.println("还活着..." + i);
	}	
	System.out.println("嗝屁了...");
	}
}

class God implements Runnable{
	@Override
	public void run() {
	for(;;) {
		System.out.println("上帝保佑你...");
	}	
	}
}