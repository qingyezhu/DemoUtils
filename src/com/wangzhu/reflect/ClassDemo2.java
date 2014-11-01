package com.wangzhu.reflect;

public class ClassDemo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Class c1 = int.class;
		Class c2 = Integer.class;
		Class c3 = void.class;
		System.out.println(c1.getName() + "," + c1.getSimpleName());
		System.out.println(c2.getName() + "," + c2.getSimpleName());
		System.out.println(c3.getName() + "," + c3.getSimpleName());

		String str = "";
		ClassUtil.printMethod(str);
		ClassUtil.printDeclaredMethod(str);
		ClassUtil.printField(str);
		ClassUtil.printDeclaredField(str);
		ClassUtil.printConstructor(str);

	}

}
