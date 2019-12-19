package com.wcc.thread;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 *  网络资源下载类 - 线程类
 */
public class IDownloader implements Runnable{
	private String source; //远程路径
	private String destination;  //存储名字
	
	public IDownloader(String source, String destination) {
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
		IDownloader td1 = new IDownloader("http://www.bokee.com/topic/images/%E6%9C%AA%E6%A0%87%E9%A2%98-1(527).jpg","p1.jpg");
		IDownloader td2 = new IDownloader("http://p1.pstatp.com/large/403c00037462ae2eee13","p2.jpg");
		IDownloader td3 = new IDownloader("http://5b0988e595225.cdn.sohucs.com/images/20170830/d8b57e0dce0d4fa29bd5ef014be663d5.jpeg","p3.jpg");
		
		new Thread(td1).start();
		new Thread(td2).start();
		new Thread(td3).start();
	}
}
