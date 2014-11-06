package com.wangzhu.reflect;

public class ClassDemo2 {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		Class c1 = int.class;
		Class c2 = Integer.class;
		Class c3 = void.class;
		System.out.println(c1.getName() + "," + c1.getSimpleName());
		System.out.println(c2.getName() + "," + c2.getSimpleName());
		System.out.println(c3.getName() + "," + c3.getSimpleName());

		// 判断是否是同一类类型(基本数据类型与其封装类的类类型是不同的)
		System.out.println(c1 == c2);// false
		// 判断是否是基本数据类型
		System.out.println(c1.isPrimitive());// true
		System.out.println(c2.isPrimitive());// false
		Class c4 = String.class;
		Class c5 = "".getClass();
		Class c6 = Class.forName("java.lang.String");

		// 判断是否是同一类类型
		System.out.println(c4 == c5);// true
		System.out.println(c4 == c6);// true

		String str = "";
		ClassUtil.printMethod(str);
		ClassUtil.printDeclaredMethod(str);
		ClassUtil.printField(str);
		ClassUtil.printDeclaredField(str);
		ClassUtil.printConstructor(str);

	}

}
