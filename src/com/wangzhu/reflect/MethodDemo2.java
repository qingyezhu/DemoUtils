package com.wangzhu.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MethodDemo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 反射的操作都是编译之后的操作
		ArrayList list = new ArrayList();
		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("hello");
		// list1.add(20);//error

		Class c = list.getClass();
		Class c1 = list1.getClass();

		// 编译之后，集合的泛型是去泛型化的
		// Java中集合的泛型，是防止错误输入的，只在编译阶段有效，操作编译就无效了
		System.out.println(c == c1);// true

		try {
			Method method = c1.getMethod("add", Object.class);
			method.invoke(list1, 100);// 绕过了编译操作就绕过了泛型
			System.out.println(list1.size());
			System.out.println(list1);
			// for (String str : list1) {
			// System.out.println(str);
			// }
			// 不能使用此方法遍历
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
