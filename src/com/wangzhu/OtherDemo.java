package com.wangzhu;

public class OtherDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("num=" + OtherDemo.num);
		System.out.println("str=" + OtherDemo.str);

		OtherDemo.copeOfArray();

		OtherDemo.forOfArray();

		Cat cat = new Cat();
		cat.say();
	}

	private static final int MAX = 2500000;

	/**
	 * ��������ʱ������ʹ��System.arraycopy
	 */
	private static void copeOfArray() {
		int[] aArr = new int[OtherDemo.MAX];
		int[] bArr = new int[OtherDemo.MAX];
		for (int i = 0; i < OtherDemo.MAX; i++) {
			aArr[i] = i;
		}

		long start = System.currentTimeMillis();
		for (int i = 0; i < OtherDemo.MAX; i++) {
			aArr[i] = bArr[i];
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		start = System.currentTimeMillis();
		System.arraycopy(aArr, 0, bArr, 0, aArr.length);
		end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	/**
	 * ѭ������ʱ������ʹ��0ֵΪ�ս�����
	 */
	private static void forOfArray() {
		int[] aArr = new int[OtherDemo.MAX];
		long start = System.currentTimeMillis();
		for (int i = 0, len = aArr.length; i < len; i++) {
			aArr[i] = i;
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		int[] bArr = new int[OtherDemo.MAX];
		start = System.currentTimeMillis();
		for (int i = aArr.length - 1; i >= 0; i--) {
			bArr[i] = i;
		}
		end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	/**
	 * static���͵ı�������ֵ��Ҫע����˳��
	 */
	static {
		OtherDemo.num = 1;
		OtherDemo.str = "str";
	}
	private static int num;
	private static String str = null;
}

class Animal {
	final void show() {
		System.out.println("Animal show");
	}
}

class Cat extends Animal {
	void say() {
		this.show();
		System.out.println("cat say");
	}
}
