package com.wcc.commons;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 *  FileUtils 工具类 读取内容
 * @author wcc
 *
 */
public class CIOTest03 {
	public static void main(String[] args) throws IOException {
		//读取文件
		String msg = FileUtils.readFileToString(new File("aaa"), "UTF-8");
		System.out.println(msg);
		//读取到字节数组
		byte[] bytes = FileUtils.readFileToByteArray(new File("aaa"));
		System.out.println(bytes.length);
		
		//逐行读取
		List<String> msgs = FileUtils.readLines(new File("aaa"), "UTF-8");
		msgs.forEach(e->System.out.println(e));
		//返回一个迭代器
		LineIterator iterator = FileUtils.lineIterator(new File("aaa"), "UTF-8");
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
