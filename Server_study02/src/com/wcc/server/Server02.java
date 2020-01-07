package com.wcc.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 	目标: 返回响应协议
 * @author wcc
 * @Date 2020年1月7日 下午10:18:04
 */
public class Server02 {
	private ServerSocket server;
	public static void main(String[] args) {
		Server02 server = new Server02();
		server.start();
	}
	/**
	 * 	启动服务
	 */
	public void start() {
		try {
			server = new ServerSocket(8888);
			receive();
		} catch (IOException e) {
			System.out.println("服务器启动失败....");
			e.printStackTrace();
		}
	}
	/**
	 * 	接收连接处理
	 */
	public void receive() {
		try {
			Socket client = server.accept();
			System.out.println("一个客户端建立了连接....");
			//获取请求协议
			InputStream is =client.getInputStream();
			byte[] datas = new byte[1024*1024];
			System.out.println(is.available());
			int len = is.read(datas);
			String requestInfo = new String(datas,0,len);
			System.out.println(requestInfo);
			
			//返回响应
			StringBuilder content = new StringBuilder();
			content.append("<html>");
			content.append("<head>");
			content.append("<title>");
			content.append("服务器响应成功");
			content.append("</title>");
			content.append("</head>");
			content.append("<body>");
			content.append("server 又回来了。。。");
			content.append("</body>");
			content.append("</html>");
			int size = content.toString().getBytes().length;//必须获取字节长度
			//1、响应行: HTTP/1.1 200 OK
			StringBuilder responseInfo =new StringBuilder();
			String blank =" "; //空格
			String CRLF = "\r\n"; //换行符
			responseInfo.append("HTTP/1.1").append(blank);
			responseInfo.append(200).append(blank);
			responseInfo.append("OK").append(CRLF);
			//2、响应头(最后一行存在空行):
			/*
			 Date:Mon,31Dec209904:25:57GMT
			Server:shsxt Server/0.0.1;charset=GBK
			Content-type:text/html
			Content-length:39725426
			 */
			responseInfo.append("Date:").append(new Date()).append(CRLF);
			responseInfo.append("Server:").append("wcc Server/0.0.1;charset=UTF-8").append(CRLF);
			responseInfo.append("Content-type:text/html").append(CRLF);
			responseInfo.append("Content-length:").append(size).append(CRLF);
			responseInfo.append(CRLF);
			//3、正文
			responseInfo.append(content.toString());
			
			//写出到客户端
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			bw.write(responseInfo.toString());
			bw.flush();
		} catch (Exception e) {
			System.out.println("客户端错误");
			e.printStackTrace();
		}
	}
	/**
	 * 	停止服务
	 */
	public void stop() {
		
	}
	
	
}
