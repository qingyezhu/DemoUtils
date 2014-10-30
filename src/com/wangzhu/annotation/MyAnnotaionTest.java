package com.wangzhu.annotation;

import java.lang.reflect.Method;

/**
 * ע�������
 * 
 * @author wangzhu
 * @date 2014-10-30����4:05:40
 * 
 */
public class MyAnnotaionTest {
	public static void main(String[] args) throws Exception {
		String clazzPath = "com.wangzhu.annotation.MyUtils";
		// ͨ����ľ���·���ַ�����ȡ�����Class��������
		Class<?> clazz = Class.forName(clazzPath);
		// ��ȡ�������������еķ���
		Method[] methodArr = clazz.getDeclaredMethods();
		for (Method method : methodArr) {
			// �÷����Ƿ���ڸ�ע��
			if (method.isAnnotationPresent(MyAnnotation.class)) {
				// ��ȡע����Ϣ
				MyAnnotation myAnnotation = method
						.getAnnotation(MyAnnotation.class);
				System.out.printf("methodName:%s,id=%s,desc=%s\n",
						method.getName(), myAnnotation.id(),
						myAnnotation.description());
			}
		}
	}
}
