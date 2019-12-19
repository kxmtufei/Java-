package com.wcc.thread;

/**
 * 	带参数，返回值
 * 
 * @author wcc
 *
 */
public class LambdaTest03 {

	public static void main(String[] args) {

		// lambda 推导必须存在类型
		IInterest interest = (int a,int b)-> {
			System.out.println("a,b-->" + a + "," + b);
			return a+b;
		};
		System.out.println(interest.lambda(1, 2));
		
		interest = (a,b)-> {
			System.out.println("a,b-->" + a + "," + b);
			return a+b;
		};
		System.out.println(interest.lambda(1, 2));
		
		interest = (a,b)-> a+b;
		System.out.println(interest.lambda(1, 2));
	}
}

interface IInterest {
	int lambda(int a,int b);
}

class Interest implements IInterest {

	@Override
	public int lambda(int a,int b) {
		System.out.println("a,b-->" + a + "," + b);
		return a+b;
	}

}