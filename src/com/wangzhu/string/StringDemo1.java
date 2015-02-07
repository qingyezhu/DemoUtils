package com.wangzhu.string;

/**
 * String类是final类，也就是说String类不能被继承，并且其成员方法都默认为final方法。<br/>
 * 
 * 
 * @author wangzhu
 * @date 2015-2-1下午5:42:19
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
