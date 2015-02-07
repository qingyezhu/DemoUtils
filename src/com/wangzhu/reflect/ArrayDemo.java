package com.wangzhu.reflect;

import java.lang.reflect.Array;

public class ArrayDemo {
	public static void main(String[] args) {
		int[] intArr = { -1, 10, 4, 2 };
		int[] newIntArr = (int[]) ArrayDemo.changeArray(intArr, 10);
		ArrayDemo.printArray(newIntArr);
		String[] strArr = { "ab", "cd", "ef", "gh", "xy" };
		String[] newStrArr = (String[]) ArrayDemo.changeArray(strArr, 10);
		ArrayDemo.printArray(newStrArr);
	}

	public static Object changeArray(Object src, int length) {
		Class<?> clazz = src.getClass();
		Class<?> aClazz = clazz.getComponentType();// 得到数组的Class对象
		Object dest = Array.newInstance(aClazz, length);// 开辟新的数组
		int len = Array.getLength(src);
		System.arraycopy(src, 0, dest, 0, len);// 拷贝内容
		return dest;
	}

	public static void printArray(Object src) {
		Class<?> clazz = src.getClass();
		// 判断是否是数组
		if (!clazz.isArray()) {
			return;
		}
		Class<?> aClazz = clazz.getComponentType();
		String name = aClazz.getName();
		int len = Array.getLength(src);
		System.out.println("数组类型： " + name + " 长度： " + len);
		for (int i = 0; i < len; i++) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(Array.get(src, i));
		}
		System.out.println();
	}

}
