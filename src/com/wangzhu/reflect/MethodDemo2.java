package com.wangzhu.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MethodDemo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// ����Ĳ������Ǳ���֮��Ĳ���
		ArrayList list = new ArrayList();
		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("hello");
		// list1.add(20);//error

		Class c = list.getClass();
		Class c1 = list1.getClass();

		// ����֮�󣬼��ϵķ�����ȥ���ͻ���
		// Java�м��ϵķ��ͣ��Ƿ�ֹ��������ģ�ֻ�ڱ���׶���Ч�������������Ч��
		System.out.println(c == c1);// true

		try {
			Method method = c1.getMethod("add", Object.class);
			method.invoke(list1, 100);// �ƹ��˱���������ƹ��˷���
			System.out.println(list1.size());
			System.out.println(list1);
			// for (String str : list1) {
			// System.out.println(str);
			// }
			// ����ʹ�ô˷�������
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
