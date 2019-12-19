package com.wcc.tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 	模拟登录 多个客户端请求
 * 	创建客户端
 * 1、建立连接: 使用Socket创建客户端 +服务的地址和端口
 * 2、操作: 输入输出流操作
 * 3、释放资源 
 * @author wcc
 * @Date 2019年12月18日 下午10:31:56
 */
public class LoginMultiClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("---------Client-----------");
		//1、建立连接: 使用Socket创建客户端 +服务的地址和端口
		Socket client = new Socket("localhost", 8888);
		//2、操作: 输入输出流操作
		Send send = new Send(client);
		send.send();
		Receive receive = new Receive(client);
		receive.receive();
		send.getDos().close();
		receive.getDis().close();
		//3、释放资源 
		client.close();	
	}
	//发送
	static class Send{
		private Socket client;
		private DataOutputStream dos;
		private BufferedReader console;
		private String msg;
		
		public Send(Socket client) {
			try {
				console = new BufferedReader(new InputStreamReader(System.in));
				this.msg = init();
				this.client=client;
				System.out.println("连接成功");
				dos = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public DataOutputStream getDos() {
			return dos;
		}
		private String init() {
			try {
				System.out.println("请输入用户名：");		
				String uname = console.readLine();
				System.out.println("请输入密码：");		
				String pwd = console.readLine();
				console.close();
				return "uname="+uname+"&"+"pwd="+pwd;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "";
		}
		private void send() {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//接收
	static class Receive{
		private Socket client;
		private DataInputStream dis;
		public Receive(Socket client) {
			try {
				this.client=client;
				//2、操作: 输入输出流操作
				dis = new DataInputStream(client.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public DataInputStream getDis() {
			return dis;
		}
		public void receive() {
			String message="";
			try {
				message = dis.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Server:" + message);
		}
		
	}
}
