package com.wcc.chat02;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 在线聊天室: 服务器 
 * 目标: 实现多个客户可以正常收发多条消息 
 * 问题: 其他客户必须等待之前的客户退出，才能继续 排队
 * @author wcc
 * @Date 2019年12月20日 下午9:41:24
 */
public class MultiClient {
	public static void main(String[] args) throws IOException {
		System.out.println("-----Client-----");
		//1、建立连接: 使用Socket创建客户端 +服务的地址和端口
		Socket client = new Socket("localhost", 8888);
		//2、客户端发送消息
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		DataInputStream dis = new DataInputStream(client.getInputStream());
		boolean isRunning = true;
		while(isRunning) {
		String msg = console.readLine();
		dos.writeUTF(msg);
		dos.flush();
		//3、获取消息
		msg = dis.readUTF();
		System.out.println(msg);
		}
		//释放资源
		dos.close();
		dis.close();
		client.close();
	}
}
