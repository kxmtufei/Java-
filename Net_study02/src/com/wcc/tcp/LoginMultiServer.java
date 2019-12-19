package com.wcc.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 	模拟登录 多个客户端请求
 * 	创建服务器
 * 1、指定端口 使用ServerSocket创建服务器
 * 2、阻塞式等待连接 accept
 * 3、操作: 输入输出流操作
 * 4、释放资源 
 * @author wcc
 * @Date 2019年12月18日 下午10:31:56
 */
public class LoginMultiServer {
	public static void main(String[] args) throws IOException {
		System.out.println("---------Server-----------");
		// 1、指定端口 使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(8888);
		// 2、阻塞式等待连接 accept
		boolean isRunning = true;
		while(isRunning) {
			Socket client = server.accept();
			System.out.println("一个客户端建立了连接...");
			new Thread(new Channel(client)).start();
		}
		server.close();
		
	}	
	static class Channel implements Runnable{
		private Socket client;
		private DataInputStream dis;
		private DataOutputStream dos;
		public Channel(Socket client) {
			this.client=client;
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//发送客户端
		private void send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//接收客户端消息
		private String receive() {
			String datas ="";
			try {
				datas = dis.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return datas;
		}
		// 4、释放资源 
		private void release() {
			try {
				if (dos != null) {
					dos.close();
				}
				if (dis != null) {
					dis.close();
				}
				if (client != null) {
					client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			// 3、操作: 输入输出流操作
			String data = "";
			try {
				data = receive();
				String uname ="";
				String pwd ="";
				//分析
				String[] dataArr = data.split("&");
				for (String info : dataArr) {
					String[] userInfo = info.split("=");
					if ("uname".equals(userInfo[0])) {
						System.out.println("用户名："+userInfo[1]);
						uname = userInfo[1];
					}else if ("pwd".equals(userInfo[0])) {
						System.out.println("密码："+userInfo[1]);
						pwd = userInfo[1];
					}
				}
				
				if ("abc".equals(uname) && "123".equals(pwd)) {
					send("登录成功，欢迎回来");
				}else {
					send("用户名或密码错误- -！");
				}
			}finally {
				release();
			}
		}
		
	}
}
