package com.wcc.thread;

/**
 *  	静态代理 - 婚庆公司 （装饰模式）
 * 	 实现公共接口
 * 	1、真实对象 （被代理）
 * 	2、代理对象
 * @author wcc
 *
 */
public class StaticProxy {
	public static void main(String[] args) {
		new WeddingCompany(new You()).happMarry();
	}
}
//公共接口
interface Marry {
	void happMarry();
}
//真实对象
class You implements Marry{

	@Override
	public void happMarry() {
		System.out.println("我和媳妇过上了没羞没躁的生活。。");
	}
}
//代理对象
class WeddingCompany implements Marry{
	private Marry targer;
	
	@Override
	public void happMarry() {
		ready();
		targer.happMarry();
		after();
	}
	
	private void ready() {
		System.out.println("婚庆公司斥资了10个亿给俺举办了盛大的婚礼。。");
	}
	
	private void after() {
		System.out.println("婚庆公司打电话让我5星好评。。");
	}
	public WeddingCompany(Marry targer) {
		this.targer = targer;
	}
	
}