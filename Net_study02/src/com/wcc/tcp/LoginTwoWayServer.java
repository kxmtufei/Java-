package com.wcc.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 	模拟登录 双向
 * 	创建服务器
 * 1、指定端口 使用ServerSocket创建服务器
 * 2、阻塞式等待连接 accept
 * 3、操作: 输入输出流操作
 * 4、释放资源 
 * @author wcc
 * @Date 2019年12月18日 下午10:31:56
 */
public class LoginTwoWayServer {
	public static void main(String[] args) throws IOException {
		System.out.println("---------Server-----------");
		// 1、指定端口 使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(8888);
		// 2、阻塞式等待连接 accept
		Socket client = server.accept();
		System.out.println("一个客户端建立了连接...");
		// 3、操作: 输入输出流操作
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String data = dis.readUTF();
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
		//响应客户端
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		if ("abc".equals(uname) && "123".equals(pwd)) {
			dos.writeUTF("登录成功，么么哒！");
		}else {
			dos.writeUTF("用户名或密码错误- -！");
		}
		dos.flush();
		dos.close();
		// 4、释放资源 
		dis.close();
		client.close();
		server.close();
		
	}	
}
