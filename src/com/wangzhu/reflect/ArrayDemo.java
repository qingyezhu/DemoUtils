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
		Class<?> aClazz = clazz.getComponentType();// �õ������Class����
		Object dest = Array.newInstance(aClazz, length);// �����µ�����
		int len = Array.getLength(src);
		System.arraycopy(src, 0, dest, 0, len);// ��������
		return dest;
	}

	public static void printArray(Object src) {
		Class<?> clazz = src.getClass();
		// �ж��Ƿ�������
		if (!clazz.isArray()) {
			return;
		}
		Class<?> aClazz = clazz.getComponentType();
		String name = aClazz.getName();
		int len = Array.getLength(src);
		System.out.println("�������ͣ� " + name + " ���ȣ� " + len);
		for (int i = 0; i < len; i++) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(Array.get(src, i));
		}
		System.out.println();
	}

}
