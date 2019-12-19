package com.wcc.commons;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 *  FileUtils 工具类 写入文件
 * @author wcc
 *
 */
public class CIOTest04 {
	public static void main(String[] args) throws IOException {
		//写入文件 true 是否追加内容
		FileUtils.write(new File("bbb"), "你是个好人，我们分手吧！\r\n", "UTF-8");
		FileUtils.writeStringToFile(new File("bbb"), "对不起，我是个坏人！\r\n", "UTF-8", true);		
		FileUtils.writeByteArrayToFile(new File("bbb"), "打扰了，买单！\r\n".getBytes("UTF-8"), true);		
		
		//写入列表  
		List<String> list = new ArrayList<String>();
		list.add("奔多霸");
		list.add("霸多奔");
		//间隔符  --!
		FileUtils.writeLines(new File("bbb"), "UTF-8", list, "--!" , true);
	}
}
