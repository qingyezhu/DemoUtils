package com.wangzhu.reflect;

public class ClassDemo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Foo foo1 = new Foo();
		// 任何一个类都是Class的实例对象

		// 每一个类都有一个隐含的静态成员变量class
		Class c1 = Foo.class;

		// 该类的对象通过getClass方法获取
		Class c2 = foo1.getClass();
		try {
			Class c3 = Class.forName("com.wangzhu.reflect.Foo");// 动态加载类
			// 一个类只可能是Class类的一个实例对象
			System.out.println(c1 == c2);
			System.out.println(c1 == c3);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 通过类的类类型创建该类的对象实例
		try {
			Foo foo = (Foo) c1.newInstance();// 需要有无参数的构造方法
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
