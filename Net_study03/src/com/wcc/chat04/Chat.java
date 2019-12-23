package com.wcc.chat04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Channel;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 在线聊天室: 服务器
 * 目标: 加入容器实现群聊
 * @author wcc
 * @Date 2019年12月20日 下午9:54:48
 */
public class Chat {
	private static CopyOnWriteArrayList<Channel> all = new CopyOnWriteArrayList<>();
	public static void main(String[] args) throws IOException {
		System.out.println("-----Server-----");
		// 1、指定端口 使用ServerSocket创建服务器
		ServerSocket server =new ServerSocket(8888);
		// 2、阻塞式等待连接 accept
		while(true) {
			Socket client =server.accept(); 
			System.out.println("一个客户端建立了连接");
			Channel c = new Channel(client);
			all.add(c);//管理所有的成员
			new Thread(c).start();
		}
	}
	//一个客户代表一个Channel
	static class Channel implements Runnable{
		private Socket client;
		private DataInputStream dis;
		private DataOutputStream dos;
		boolean isRunning;
		private String name;
		public Channel(Socket client) {
			this.client=client;
			try {
				dis = new DataInputStream(client.getInputStream());
				dos =new DataOutputStream(client.getOutputStream());
				isRunning = true;
				//获取名称
				this.name = receive();
				//欢迎你的到来
				//欢迎你的到来
				this.send("欢迎 [" + this.name + "]进入了聊天室...");
				sendOthers("欢迎 [" + this.name + "]进入了聊天室...", true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		//释放资源
		private void release() {
			isRunning = false;
			ChatUtil.close(dis,dos,client);
			//退出
			all.remove(this);
			sendOthers(this.name+" 离开了聊天室...",true);

		}
		/**
		 * 群聊: 获取自己的消息，发给其他人
		 * @param msg
		 * @param isSys 是否为系统消息
		 */
		private void sendOthers(String msg,boolean isSys) {
			for(Channel other: all) {
				if (other == this) {//自己
					continue;
				}
				if (!isSys) {
					other.send(this.name +"对所有人说： "+msg);//群聊消息
				} else {
					other.send("系统消息：" + msg);//系统消息
				}
			}
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
//					send(msg);
					sendOthers(msg,false);
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
