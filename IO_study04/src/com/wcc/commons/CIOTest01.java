package com.wcc.commons;

import java.io.File;

import org.apache.commons.io.FileUtils;

/**
 *  FileUtils 工具类
 * @author wcc
 *
 */
public class CIOTest01 {
	public static void main(String[] args) {
		//文件大小
		long len = FileUtils.sizeOf(new File("src/com/wcc/commons/CIOTest01.java"));
		System.out.println(len);
		//目录大小
		len = FileUtils.sizeOf(new File("D:/eclipse-workspace2/IO_study04"));
		System.out.println(len);
	}
}
