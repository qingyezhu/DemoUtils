package com.wangzhu.string;

/**
 * ���ݿ��Խ����޸ģ������䲢û����дequals��hashcode����<br/>
 * 
 * @author wangzhu
 * @date 2015-2-1����5:49:02
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
