package com.wcc.thread;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 *  网络资源下载类 - 线程类
 */
public class TDownloader extends Thread{
	private String source; //远程路径
	private String destination;  //存储名字
	
	public TDownloader(String source, String destination) {
		super();
		this.source = source;
		this.destination = destination;
	}
	
	@Override
		public void run() {
			WebDownloader wdl = new WebDownloader();
			try {
				wdl.download(source, destination);
				System.out.println(destination);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("source路径不合法");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("下载失败");
			}
		}
	public static void main(String[] args) {
		TDownloader td1 = new TDownloader("http://www.bokee.com/topic/images/%E6%9C%AA%E6%A0%87%E9%A2%98-1(527).jpg","p1.jpg");
		TDownloader td2 = new TDownloader("http://p1.pstatp.com/large/403c00037462ae2eee13","p2.jpg");
		TDownloader td3 = new TDownloader("http://5b0988e595225.cdn.sohucs.com/images/20170830/d8b57e0dce0d4fa29bd5ef014be663d5.jpeg","p3.jpg");
		
		td1.start();
		td2.start();
		td3.start();
	}
}
