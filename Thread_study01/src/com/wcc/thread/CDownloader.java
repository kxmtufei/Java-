package com.wcc.thread;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 	创建线程方式三：实现callable+重写call方法
 * 	网络资源下载类 - 线程类
 */
public class CDownloader implements Callable<Boolean> {
	private String source; // 远程路径
	private String destination; // 存储名字

	public CDownloader(String source, String destination) {
		super();
		this.source = source;
		this.destination = destination;
	}

	@Override
	public Boolean call() throws Exception {
		WebDownloader wdl = new WebDownloader();
		wdl.download(source, destination);
		System.out.println(destination);
		return true;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CDownloader td1 = new CDownloader("http://www.bokee.com/topic/images/%E6%9C%AA%E6%A0%87%E9%A2%98-1(527).jpg",
				"p1.jpg");
		CDownloader td2 = new CDownloader("http://p1.pstatp.com/large/403c00037462ae2eee13", "p2.jpg");
		CDownloader td3 = new CDownloader(
				"http://5b0988e595225.cdn.sohucs.com/images/20170830/d8b57e0dce0d4fa29bd5ef014be663d5.jpeg", "p3.jpg");
		
		//创建执行服务: 
		ExecutorService ser = Executors.newFixedThreadPool(3);
		//提交执行: 
		Future<Boolean> result1 = ser.submit(td1);
		Future<Boolean> result2 = ser.submit(td2);
		Future<Boolean> result3 = ser.submit(td3);
		//获取结果:
		Boolean r1 = result1.get();
		Boolean r2 = result2.get();
		Boolean r3 = result3.get();
		System.out.println(r3);
		//关闭服务:  
		ser.shutdown();
	}
}
