package com.wangzhu.string;

public class StringDemo2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str1 = "a1";
		String str2 = "a" + 1;
		System.out.println(str1 == str2);// true

		/**
		 * �������ڳ�������ڼ䣬JVM�ͽ��ַ���������+�����Ż�Ϊ���Ӻ��ֵ�����ڱ������ַ���������ֵ��ȷ�������ˡ�<br/>
		 */

		String str21 = "ab";
		String str22 = "b";
		String str23 = "a" + str22;
		System.out.println(str21 == str23);// false
		/**
		 * ������JVM�����ַ������ã��������ַ�����+�����У����ַ������õĴ��ڣ������õ�ֵ�ڳ���������޷�ȷ����<br/>
		 * ֻ���ڳ�������������̬���䲢�����Ӻ���µ�ַ����������
		 */

		String str31 = "ab";
		final String str32 = "b";
		String str33 = "a" + str32;
		System.out.println(str31 == str33);// true
		/**
		 * ����������final���εı��������ڱ���ʱ������Ϊ����ֵ��һ�����ؿ����洢���Լ��ĳ������л�Ƕ�뵽���ֽ������С�<br/>
		 */

		System.out.println(str21 == str23.intern());// true
		/**
		 * ������ʹ��String��intern�������᷵�ظ��ַ����ڳ������еĵ�ֵַ��<br/>
		 */
	}

}
