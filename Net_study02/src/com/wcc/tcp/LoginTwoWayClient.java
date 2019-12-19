package com.wcc.tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 	模拟登录 双向
 * 	创建客户端
 * 1、建立连接: 使用Socket创建客户端 +服务的地址和端口
 * 2、操作: 输入输出流操作
 * 3、释放资源 
 * @author wcc
 * @Date 2019年12月18日 下午10:31:56
 */
public class LoginTwoWayClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("---------Client-----------");
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入用户名：");		
		String uname = console.readLine();
		System.out.println("请输入密码：");		
		String pwd = console.readLine();
		console.close();
		//1、建立连接: 使用Socket创建客户端 +服务的地址和端口
		Socket client = new Socket("localhost", 8888);
		System.out.println("连接成功");
		//2、操作: 输入输出流操作
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		String data = "uname="+uname+"&"+"pwd="+pwd;
		dos.writeUTF(data);
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String message = dis.readUTF();
		System.out.println("Server:" + message);
		dis.close();
		//3、释放资源 
		dos.flush();
		client.close();
		
	}
}
