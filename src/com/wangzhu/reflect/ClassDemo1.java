package com.wangzhu.reflect;

public class ClassDemo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Foo foo1 = new Foo();
		// �κ�һ���඼��Class��ʵ������

		// ÿһ���඼��һ�������ľ�̬��Ա����class
		Class c1 = Foo.class;

		// ����Ķ���ͨ��getClass������ȡ
		Class c2 = foo1.getClass();
		try {
			Class c3 = Class.forName("com.wangzhu.reflect.Foo");// ��̬������
			// һ����ֻ������Class���һ��ʵ������
			System.out.println(c1 == c2);
			System.out.println(c1 == c3);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// ͨ����������ʹ�������Ķ���ʵ��
		try {
			Foo foo = (Foo) c1.newInstance();// ��Ҫ���޲����Ĺ��췽��
			foo.print();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}

class Foo {
	void print() {
		System.out.println("foo");
	}
}
