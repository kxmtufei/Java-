package com.wcc.chat03;

import java.io.IOException;
import java.net.Socket;

/**
 * 在线聊天室: 服务器
 * 目标: 使用多线程实现多个客户可以正常收发多条消息 
 * 问题: 
 * 1、代码不好维护
 * 2、客户端读写没有分开 必须先写后读
 * @author wcc
 * @Date 2019年12月20日 下午9:41:24
 */
public class TMultiClient {
	public static void main(String[] args) throws IOException {
		System.out.println("-----Client-----");
		//1、建立连接: 使用Socket创建客户端 +服务的地址和端口
		Socket client = new Socket("localhost", 8888);
		//2、客户端发送消息
		new Thread(new Send(client)).start();
		//3、获取消息
		new Thread(new Receive(client)).start();
		
	}
}
