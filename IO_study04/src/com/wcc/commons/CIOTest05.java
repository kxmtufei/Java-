package com.wcc.commons;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 *  FileUtils 工具类 拷贝
 * @author wcc
 *
 */
public class CIOTest05 {
	public static void main(String[] args) throws IOException {
		//复制文件
//		FileUtils.copyFile(new File("aaa"), new File("aaa-copy"));
		//复制文件到指定目录
//		FileUtils.copyFileToDirectory(new File("aaa"), new File("lib"));
		//复制目录到目录(包括目录)
//		FileUtils.copyDirectoryToDirectory(new File("lib"), new File("lib2"));
		//复制目录(不包括目录)
//		FileUtils.copyDirectory(new File("lib"), new File("lib2"));
		//拷贝URL内容
//		String url = "https://pic2.zhimg.com/v2-7d01cab20858648cbf62333a7988e6d0_qhd.jpg";
//		FileUtils.copyURLToFile(new URL(url), new File("marvel.jpg"));
		String datas = IOUtils.toString(new URL("http://www.baidu.com"), "UTF-8");
		System.out.println(datas);
		
	}
}
