package com.wcc.thread;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

/**
 *  网络资源下载类
 */
public class WebDownloader {

	public void download(String source, String destination) throws MalformedURLException, IOException {
		FileUtils.copyURLToFile(new URL(source), new File(destination));
	}
}
