package com.wangzhu.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {

	/**
	 * 获取所有的public权限的成员方法
	 * 
	 * @param obj
	 */
	public static void printMethod(Object obj) {
		System.out.println("public 权限的成员方法");
		Class c = obj.getClass();
		// 类的名称
		System.out.println(c.getName());
		// 获取所有的public权限的方法
		Method[] methodArr = c.getMethods();
		ClassUtil.printMethod(methodArr);

	}

	/**
	 * 获取所有该类自己声明的成员方法
	 * 
	 * @param obj
	 */
	public static void printDeclaredMethod(Object obj) {
		System.out.println("自己声明的成员方法");
		Class c = obj.getClass();
		// 类的名称
		System.out.println(c.getName());
		// 获取所有该类自己声明的成员方法
		Method[] methodArr = c.getDeclaredMethods();
		ClassUtil.printMethod(methodArr);
	}

	/**
	 * 打印成员方法信息
	 * 
	 * @param methodArr
	 */
	private static void printMethod(Method[] methodArr) {
		for (Method method : methodArr) {
			// 返回值类型的类类型
			Class returnType = method.getReturnType();
			System.out.print(returnType.getName() + " ");
			System.out.print(method.getName());
			// 参数列表类型的类类型
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
				// 参数类型名
				System.out.print(parameterTypeArr[i].getName());
				if (i == (len - 1)) {
					System.out.println(")");
				}
			}
		}
	}

	/**
	 * 获取所有public权限的成员变量
	 * 
	 * @param obj
	 */
	public static void printField(Object obj) {
		System.out.println("public权限的成员变量");
		Class c = obj.getClass();
		// 类的名称
		System.out.println(c.getName());
		// 获取所有public权限的成员变量
		Field[] fieldArr = c.getFields();
		ClassUtil.printField(fieldArr);
	}

	/**
	 * 获取该类自己声明的成员变量
	 * 
	 * @param obj
	 */
	public static void printDeclaredField(Object obj) {
		System.out.println("自己声明的成员变量");
		Class c = obj.getClass();
		// 类的名称
		System.out.println(c.getName());
		// 获取该类自己声明的成员变量
		Field[] fieldArr = c.getDeclaredFields();
		ClassUtil.printField(fieldArr);
	}

	/**
	 * 打印成员变量的信息
	 * 
	 * @param fieldArr
	 */
	private static void printField(Field[] fieldArr) {
		for (Field field : fieldArr) {
			// 变量的类型的类类型
			Class typeClass = field.getType();
			// 变量的类型的名称
			String typeName = typeClass.getName();
			// 变量名
			String fieldName = field.getName();
			System.out.println(typeName + " " + fieldName);
		}
	}

	/**
	 * 获取自己声明的所有的构造方法
	 * 
	 * @param obj
	 */
	public static void printConstructor(Object obj) {
		System.out.println("自己声明的所有的构造方法");
		Class c = obj.getClass();
		// 得到所有的public权限的构造方法
		// Constructor[] constructorArr = c.getConstructors();
		// 获取自己声明的所有的构造方法
		Constructor[] constructorArr = c.getDeclaredConstructors();
		for (Constructor constructor : constructorArr) {
			// 构造方法名
			System.out.print(constructor.getName());
			// 构造方法参数列表类型的类类型
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
