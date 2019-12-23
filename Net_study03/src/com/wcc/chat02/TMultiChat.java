package com.wcc.chat02;

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
			Socket  client =server.accept(); 
			System.out.println("一个客户端建立了连接");
			new Thread(()-> {
				DataInputStream dis = null;
				DataOutputStream dos = null;
				try {
					dis = new DataInputStream(client.getInputStream());
					dos =new DataOutputStream(client.getOutputStream());		
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				boolean isRunning = true;
				while(isRunning) {
					//3、接收消息
					String msg;
					try {
						msg = dis.readUTF();
						//4、返回消息
						dos.writeUTF(msg);
						dos.flush();
					} catch (IOException e) {
						//e.printStackTrace();
						isRunning = false; //停止线程
					}
				}
				//释放资源
				try {
					if (dos!=null) {
						dos.close();
					}
					if (dis!=null) {
						dis.close();
					}
					if (client!=null) {
						client.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}).start();
		}
		
			
			
		
	}
}
