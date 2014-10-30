package com.wangzhu.annotation;

/**
 * ����ע�����
 * 
 * @author wangzhu
 * @date 2014-10-30����4:05:57
 * 
 */
public class MyUtils {
	@MyAnnotation(id = "10000", description = "�ж��Ƿ������ִ�")
	public boolean isNumber(String str) {
		return str.matches("\\d*");
	}

	@MyAnnotation(id = "10001")
	public String reverseStr(String str) {
		return new StringBuffer(str).reverse().toString();
	}

}
