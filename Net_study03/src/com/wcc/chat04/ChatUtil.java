package com.wcc.chat04;
/**
 * 工具类
 */
import java.io.Closeable;
import java.io.IOException;

public class ChatUtil {
	/**
	 * 释放资源
	 */
	public static void close(Closeable...targets) {
		for (Closeable target : targets) {
				try {
					if (null != target) {
						target.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
