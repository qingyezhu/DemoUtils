package com.wangzhu.string;

/**
 * String����final�࣬Ҳ����˵String�಻�ܱ��̳У��������Ա������Ĭ��Ϊfinal������<br/>
 * 
 * 
 * @author wangzhu
 * @date 2015-2-1����5:42:19
 * 
 */
public class StringDemo1 {

	public static void main(String[] args) {
		String str = "";
		for (int i = 0; i < 10000; i++) {
			str += "test";
		}

	}
}
