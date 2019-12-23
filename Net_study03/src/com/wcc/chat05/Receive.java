package com.wcc.chat05;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable{
	private Socket client;
	private DataInputStream dis;
	private boolean isRunning;
	public Receive(Socket client) {
		// TODO Auto-generated constructor stub
		this.client=client;
		this.isRunning = true;
		try {
			dis = new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			System.out.println("客户端接收端->启动失败！");
			release();
		}
	}
	//发送消息
	private String receive() {
		//3、获取消息
		try {
			return dis.readUTF();
		} catch (IOException e) {
			System.out.println("客户端接收端->接收消息失败！");
			 release();
			e.printStackTrace();
		}
		return "";
	}
	//释放资源
	private void release() {
		isRunning = false;
		ChatUtil.close(dis,client);
	}
	@Override
	public void run() {
		while(isRunning) {
			//3、获取消息
			String msg = receive();
			if (!"".equals(msg)) {
				System.out.println(msg);
				if ("bye".equals(msg)) {
					isRunning = false;	
					release();
					System.out.println("下线咯...");
				}
			}
			}
	}
}
