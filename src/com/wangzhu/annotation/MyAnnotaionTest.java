package com.wangzhu.annotation;

import java.lang.reflect.Method;

/**
 * 注解测试类
 * 
 * @author wangzhu
 * @date 2014-10-30下午4:05:40
 * 
 */
public class MyAnnotaionTest {
	public static void main(String[] args) throws Exception {
		String clazzPath = "com.wangzhu.annotation.MyUtils";
		// 通过类的绝对路径字符串获取该类的Class类的类对象
		Class<?> clazz = Class.forName(clazzPath);
		// 获取该类声明的所有的方法
		Method[] methodArr = clazz.getDeclaredMethods();
		for (Method method : methodArr) {
			// 该方法是否存在该注解
			if (method.isAnnotationPresent(MyAnnotation.class)) {
				// 获取注解信息
				MyAnnotation myAnnotation = method
						.getAnnotation(MyAnnotation.class);
				System.out.printf("methodName:%s,id=%s,desc=%s\n",
						method.getName(), myAnnotation.id(),
						myAnnotation.description());
			}
		}
	}
}
