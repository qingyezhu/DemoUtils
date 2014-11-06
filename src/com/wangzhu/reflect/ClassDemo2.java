package com.wangzhu.reflect;

public class ClassDemo2 {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		Class c1 = int.class;
		Class c2 = Integer.class;
		Class c3 = void.class;
		System.out.println(c1.getName() + "," + c1.getSimpleName());
		System.out.println(c2.getName() + "," + c2.getSimpleName());
		System.out.println(c3.getName() + "," + c3.getSimpleName());

		// �ж��Ƿ���ͬһ������(�����������������װ����������ǲ�ͬ��)
		System.out.println(c1 == c2);// false
		// �ж��Ƿ��ǻ�����������
		System.out.println(c1.isPrimitive());// true
		System.out.println(c2.isPrimitive());// false
		Class c4 = String.class;
		Class c5 = "".getClass();
		Class c6 = Class.forName("java.lang.String");

		// �ж��Ƿ���ͬһ������
		System.out.println(c4 == c5);// true
		System.out.println(c4 == c6);// true

		String str = "";
		ClassUtil.printMethod(str);
		ClassUtil.printDeclaredMethod(str);
		ClassUtil.printField(str);
		ClassUtil.printDeclaredField(str);
		ClassUtil.printConstructor(str);

	}

}
