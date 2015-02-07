package com.wangzhu.string;

/**
 * 内容可以进行修改，并且其并没有重写equals与hashcode方法<br/>
 * 
 * @author wangzhu
 * @date 2015-2-1下午5:49:02
 * 
 */
public class StringBuilderDemo1 {

	public static void main(String[] args) {
		StringBuilder accum = new StringBuilder();
		for (int i = 0; i < 10000; i++) {
			accum.append("test");
		}
	}
}
