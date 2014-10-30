package com.wangzhu.annotation;

/**
 * 标有注解的类
 * 
 * @author wangzhu
 * @date 2014-10-30下午4:05:57
 * 
 */
public class MyUtils {
	@MyAnnotation(id = "10000", description = "判断是否是数字串")
	public boolean isNumber(String str) {
		return str.matches("\\d*");
	}

	@MyAnnotation(id = "10001")
	public String reverseStr(String str) {
		return new StringBuffer(str).reverse().toString();
	}

}
