package com.wcc.thread;

/**
 * 	带参数
 * 
 * @author wcc
 *
 */
public class LambdaTest02 {

	public static void main(String[] args) {

		// lambda 推导必须存在类型
		ILove love = (String msg) -> {
			System.out.println("我喜欢吃-->" + msg);
		};
		love.lambda("大西瓜");

		// 简化 类型可以省略
		love = (msg) -> {
			System.out.println("我喜欢吃-->" + msg);
		};
		love.lambda("大冬瓜");

		// 简化 只有一个参数，括号可以省略
		love = msg -> {
			System.out.println("我喜欢吃-->" + msg);
		};
		love.lambda("大南瓜");

		// 简化 只有一行代码，花括号可以省略
		love = msg -> System.out.println("我喜欢吃-->" + msg);
		love.lambda("大窝瓜");
	}
}

interface ILove {
	void lambda(String msg);
}

class Love implements ILove {

	@Override
	public void lambda(String msg) {
		System.out.println("我喜欢吃-->" + msg);

	}

}