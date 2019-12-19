package com.wcc.commons;


import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.EmptyFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;

/**
 *  FileUtils 工具类  列出子目录
 * @author wcc
 *
 */
public class CIOTest02 {
	public static void main(String[] args) {
		//列出当前目录下的非空文件（子级）
		Collection<File> files = FileUtils.listFiles(new File("D:/eclipse-workspace2/IO_study04"), 
				EmptyFileFilter.NOT_EMPTY, null);
		files.forEach(e->System.out.println(e.getAbsoluteFile()));
		System.out.println("======================");
		//列出当前目录下的非空文件（子孙级）
		files = FileUtils.listFiles(new File("D:/eclipse-workspace2/IO_study04"), 
				EmptyFileFilter.NOT_EMPTY, DirectoryFileFilter.INSTANCE);
		files.forEach(e->System.out.println(e.getAbsoluteFile()));
		System.out.println("======================");
		//列出当前目录下的java文件（子孙级）
		files = FileUtils.listFiles(new File("D:/eclipse-workspace2/IO_study04"), 
				new SuffixFileFilter("java"), DirectoryFileFilter.INSTANCE);
		files.forEach(e->System.out.println(e.getAbsoluteFile()));
		System.out.println("======================");
		//列出当前目录下的java和class文件（子孙级）
		files = FileUtils.listFiles(new File("D:/eclipse-workspace2/IO_study04"), 
				FileFilterUtils.or(new SuffixFileFilter("java"),
						new SuffixFileFilter("class")), DirectoryFileFilter.INSTANCE);
		files.forEach(e->System.out.println(e.getAbsoluteFile()));
		System.out.println("======================");
		//列出当前目录下的非空java文件（子孙级）
		files = FileUtils.listFiles(new File("D:/eclipse-workspace2/IO_study04"), 
				FileFilterUtils.and(new SuffixFileFilter("java"),EmptyFileFilter.NOT_EMPTY),
				DirectoryFileFilter.INSTANCE);
		files.forEach(e->System.out.println(e.getAbsoluteFile()));
		
	}
}
