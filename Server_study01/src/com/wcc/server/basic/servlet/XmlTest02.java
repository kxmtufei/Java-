package com.wcc.server.basic.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 熟悉SAX解析流程
 * @author wcc
 * @Date 2019年12月23日 下午11:21:45
 */
public class XmlTest02 {
	public static void main(String[] args) throws Exception {
		//SAX解析
		//1、获取解析工厂
		SAXParserFactory factory=SAXParserFactory.newInstance();
		//2、从解析工厂获取解析器
		SAXParser parse =factory.newSAXParser();
		//3、编写处理器
		//4、加载文档 Document 注册处理器
		WebHandler handler=new WebHandler();
		//5、解析
		parse.parse(Thread.currentThread().getContextClassLoader()
		.getResourceAsStream("com/wcc/server/basic/servlet/web.xml")
		,handler);

		//获取数据
		WebContext context = new WebContext(handler.getEntitys(), handler.getMappings());
		String name = context.getClz("/reg");
		Class clz = Class.forName(name);
		Servlet servlet = (Servlet)clz.getConstructor().newInstance();
		System.out.println(servlet);
		servlet.service();
	}
}
class WebHandler extends DefaultHandler{
	private List<Entity> entitys;
	private List<Mapping> mappings ;
	private Entity entity;
	private Mapping mapping;
	private boolean isMapping;
	String tag;//存储操作标签

	@Override
	public void startDocument() throws SAXException {
		System.out.println("----解析文档开始----");
		entitys = new ArrayList<Entity>();
		mappings = new ArrayList<Mapping>();
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println(qName+"-->解析开始");
		if (null != qName) {
			tag = qName;
			if (tag.equals("servlet")) {
				entity = new Entity();
				isMapping = false;
			}else if (tag.equals("servlet-mapping")) {
				mapping = new Mapping();
				isMapping = true;
			}	
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String contents = new String(ch,start,length).trim();
		if(null!=tag) { //处理了空
			if (isMapping) {
				if (tag.equals("servlet-name") && contents.length()>0) {
					mapping.setName(contents);
				}else if (tag.equals("url-pattern") && contents.length()>0) {
					mapping.addPattern(contents);
				}
			}else {
				if (tag.equals("servlet-name") && contents.length()>0) {
					entity.setName(contents);
				}else if (tag.equals("servlet-class") && contents.length()>0) {
					entity.setClz(contents);
				}
			}
			
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println(qName+"-->解析结束");
		if (qName.equals("servlet")) {
			entitys.add(entity);
		}else if (qName.equals("servlet-mapping")) {
			mappings.add(mapping);
		}	
		tag=null;//解析结束，tag丢弃了
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println("----解析文档结束----");

	}
	public List<Entity> getEntitys() {
		return entitys;
	}

	public List<Mapping> getMappings() {
		return mappings;
	}
	
}