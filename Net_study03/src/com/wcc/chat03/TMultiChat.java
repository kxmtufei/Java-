package com.wcc.chat03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室: 服务器
 * 目标: 使用多线程实现多个客户可以正常收发多条消息 
 * 问题: 
 * 1、代码不好维护
 * 2、客户端读写没有分开 必须先写后读
 * @author wcc
 * @Date 2019年12月20日 下午9:54:48
 */
public class TMultiChat {
	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
		// 1、指定端口 使用ServerSocket创建服务器
		ServerSocket server =new ServerSocket(8888);
		// 2、阻塞式等待连接 accept
		while(true) {
			Socket client =server.accept(); 
			System.out.println("一个客户端建立了连接");
			new Thread(new channel(client)).start();
		}
	}
	//一个客户代表一个Channel
	static class channel implements Runnable{
		private Socket client;
		private DataInputStream dis;
		private DataOutputStream dos;
		boolean isRunning = true;
		public channel(Socket client) {
			this.client=client;
			try {
				dis = new DataInputStream(client.getInputStream());
				dos =new DataOutputStream(client.getOutputStream());		
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		//释放资源
		private void release() {
			isRunning = false;
			ChatUtil.close(dis,dos,client);
		}
		//发送消息
		private void send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				System.out.println("服务器->发送消息失败！");
				release();
			}
		}
		//接收消息
		private String receive() {
			String msg = "";
			try {
				 msg = dis.readUTF();
			} catch (IOException e) {
				System.out.println("服务器->接收消息失败！");
				release();
			}
			return msg;
		}
		@Override
		public void run() {
			while(isRunning) {
				//3、接收消息
				String msg;
				msg = receive();
				//4、返回消息
				if (!msg.equals("")) {
					send(msg);
					if ("bye".equals(msg)) {
						isRunning = false;	
						release();
						System.out.println("一个客户端断开连接...");
					}
				}
				
			}
			
		}
	}
}
