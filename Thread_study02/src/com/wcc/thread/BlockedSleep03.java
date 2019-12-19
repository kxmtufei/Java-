package com.wcc.thread;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *	模拟倒计时
 * @author wcc
 * @Date 2019年12月9日 下午10:45:45
 */
public class BlockedSleep03 {
	public static void main(String[] args) throws InterruptedException {
		//倒计时 10秒
		Date endTime = new Date(System.currentTimeMillis() + 1000*10);
		long end = endTime.getTime();
		while(true) {
			Thread.sleep(1000);
			System.out.println(new SimpleDateFormat("mm:ss").format(endTime));
			endTime = new Date(endTime.getTime() - 1000);
			if (end-1000*10>endTime.getTime()) {
				break;
			}
		}
	}
}
