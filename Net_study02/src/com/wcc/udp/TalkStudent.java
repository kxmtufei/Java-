package com.wcc.udp;
/**
 * 加入多线程，实现双向交流 模拟在线咨询
 * @author wcc
 * @Date 2019年12月18日 下午10:09:23
 */
public class TalkStudent {
	public static void main(String[] args) {
		System.out.println("学生上线...");
		new Thread(new TalkSend(6666, "localhost", 9999)).start();//发送
		new Thread(new TalkReceive(7777,"老师")).start();//接收 
	}
}
