package com.wangzhu.reflect;

import java.lang.reflect.Method;

public class MethodDemo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		A a1 = new A();
		Class c = a1.getClass();
		// ��ȡpublicȨ�޵ķ���
		try {
			Method method1 = c.getMethod("print", int.class, int.class);
			// û�з���ֵ��Ϊnull
			method1.invoke(a1, 1, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ȡ�Լ������ķ���
		try {
			Method method2 = c.getDeclaredMethod("print", new Class[] {
					String.class, String.class });
			method2.invoke(a1, new Object[] { "Hello", "World" });
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class A {
	public void print(int a, int b) {
		System.out.println(a + b);
	}

	public void print(String a, String b) {
		System.out.println(a.toUpperCase() + "," + b.toLowerCase());
	}
}
