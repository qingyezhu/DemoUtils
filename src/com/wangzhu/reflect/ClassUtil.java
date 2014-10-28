package com.wangzhu.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {

	/**
	 * ��ȡ���е�publicȨ�޵ĳ�Ա����
	 * 
	 * @param obj
	 */
	public static void printMethod(Object obj) {
		System.out.println("public Ȩ�޵ĳ�Ա����");
		Class c = obj.getClass();
		// �������
		System.out.println(c.getName());
		// ��ȡ���е�publicȨ�޵ķ���
		Method[] methodArr = c.getMethods();
		ClassUtil.printMethod(methodArr);

	}

	/**
	 * ��ȡ���и����Լ������ĳ�Ա����
	 * 
	 * @param obj
	 */
	public static void printDeclaredMethod(Object obj) {
		System.out.println("�Լ������ĳ�Ա����");
		Class c = obj.getClass();
		// �������
		System.out.println(c.getName());
		// ��ȡ���и����Լ������ĳ�Ա����
		Method[] methodArr = c.getDeclaredMethods();
		ClassUtil.printMethod(methodArr);
	}

	/**
	 * ��ӡ��Ա������Ϣ
	 * 
	 * @param methodArr
	 */
	private static void printMethod(Method[] methodArr) {
		for (Method method : methodArr) {
			// ����ֵ���͵�������
			Class returnType = method.getReturnType();
			System.out.print(returnType.getName() + " ");
			System.out.print(method.getName());
			// �����б����͵�������
			Class[] parameterTypeArr = method.getParameterTypes();
			int len = parameterTypeArr.length;
			if (len == 0) {
				System.out.println("()");
				continue;
			}
			for (int i = 0; i < len; i++) {
				if (i == 0) {
					System.out.print("(");
				} else {
					System.out.print(",");
				}
				// ����������
				System.out.print(parameterTypeArr[i].getName());
				if (i == (len - 1)) {
					System.out.println(")");
				}
			}
		}
	}

	/**
	 * ��ȡ����publicȨ�޵ĳ�Ա����
	 * 
	 * @param obj
	 */
	public static void printField(Object obj) {
		System.out.println("publicȨ�޵ĳ�Ա����");
		Class c = obj.getClass();
		// �������
		System.out.println(c.getName());
		// ��ȡ����publicȨ�޵ĳ�Ա����
		Field[] fieldArr = c.getFields();
		ClassUtil.printField(fieldArr);
	}

	/**
	 * ��ȡ�����Լ������ĳ�Ա����
	 * 
	 * @param obj
	 */
	public static void printDeclaredField(Object obj) {
		System.out.println("�Լ������ĳ�Ա����");
		Class c = obj.getClass();
		// �������
		System.out.println(c.getName());
		// ��ȡ�����Լ������ĳ�Ա����
		Field[] fieldArr = c.getDeclaredFields();
		ClassUtil.printField(fieldArr);
	}

	/**
	 * ��ӡ��Ա��������Ϣ
	 * 
	 * @param fieldArr
	 */
	private static void printField(Field[] fieldArr) {
		for (Field field : fieldArr) {
			// ���������͵�������
			Class typeClass = field.getType();
			// ���������͵�����
			String typeName = typeClass.getName();
			// ������
			String fieldName = field.getName();
			System.out.println(typeName + " " + fieldName);
		}
	}

	/**
	 * ��ȡ�Լ����������еĹ��췽��
	 * 
	 * @param obj
	 */
	public static void printConstructor(Object obj) {
		System.out.println("�Լ����������еĹ��췽��");
		Class c = obj.getClass();
		// �õ����е�publicȨ�޵Ĺ��췽��
		// Constructor[] constructorArr = c.getConstructors();
		// ��ȡ�Լ����������еĹ��췽��
		Constructor[] constructorArr = c.getDeclaredConstructors();
		for (Constructor constructor : constructorArr) {
			// ���췽����
			System.out.print(constructor.getName());
			// ���췽�������б����͵�������
			Class[] parameterTypeArr = constructor.getParameterTypes();
			int len = parameterTypeArr.length;
			if (len == 0) {
				System.out.println("()");
				continue;
			}
			for (int i = 0; i < len; i++) {
				if (i == 0) {
					System.out.print("(");
				} else {
					System.out.print(",");
				}
				System.out.print(parameterTypeArr[i].getName());
				if (i == (len - 1)) {
					System.out.println(")");
				}
			}
		}
	}

}
