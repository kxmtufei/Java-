package com.wcc.thread;

/**
 * 	无参数，无返回值
 * @author wcc
 *
 */
public class LambdaTest01 {
	//静态内部类
	static class Like2 implements ILike {
		@Override
		public void lambda() {
			System.out.println("我宣你，欧皓辰2！");
		}
	}

	public static void main(String[] args) {
		ILike like = new Like();
		like.lambda();

		like = new Like2();
		like.lambda();

		// 匿名内部类
		like = new ILike() {
			@Override
			public void lambda() {
				System.out.println("我宣你，欧皓辰3！");
			}
		};
		like.lambda();

		// lambda 推导必须存在类型
		like = () -> {
			System.out.println("我宣你，欧皓辰4！");
		};
		like.lambda();
	}
}

interface ILike {
	void lambda();
}

class Like implements ILike {

	@Override
	public void lambda() {
		System.out.println("我宣你，欧皓辰！");

	}

}