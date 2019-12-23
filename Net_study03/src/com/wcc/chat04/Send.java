package com.wcc.chat04;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable {
	private Socket client;
	private BufferedReader console;
	private DataOutputStream dos;
	private boolean isRunning;
	private String name;
	public Send(Socket client,String name) {
		this.client=client;
		console = new BufferedReader(new InputStreamReader(System.in));
		this.isRunning = true;
		this.name = name;
		try {
			dos = new DataOutputStream(client.getOutputStream());
			send(name);//发送用户名
		} catch (IOException e) {
			System.out.println("客户端发送端->启动失败！");
			release();
		}
	}
	/**
	 * 从控制台获取消息
	 * @return
	 */
	private String getStrFromConsole() {
		String msg = "";
		try {
			msg = console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
	//发送消息
	private void send(String msg) {
		try {
			dos.writeUTF(msg);
			dos.flush();
		} catch (IOException e) {
			System.out.println("客户端发送方->发送消息失败！");
			System.out.println(e);
			release();
		}
	}
	//释放资源
	private void release() {
		isRunning = false;
		ChatUtil.close(dos,client);
	}
	@Override
	public void run() {
		while(isRunning) {
			String msg = getStrFromConsole();
			if (!"".equals(msg)) {
				send(msg);
			}
		}
	}
	
}
