package com.wcc.chat01;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 在线聊天室: 客户端
 * 目标: 实现一个客户可以正常收发消息
 * @author wcc
 * @Date 2019年12月20日 下午9:41:24
 */
public class Client {
	public static void main(String[] args) throws IOException {
		System.out.println("-----Client-----");
		//1、建立连接: 使用Socket创建客户端 +服务的地址和端口
		Socket client = new Socket("localhost", 8888);
		//2、客户端发送消息
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		String msg = console.readLine();
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		dos.writeUTF(msg);
		dos.flush();
		//3、获取消息
		DataInputStream dis = new DataInputStream(client.getInputStream());
		msg = dis.readUTF();
		System.out.println(msg);
		//释放资源
		dos.close();
		dis.close();
		client.close();
	}
}
