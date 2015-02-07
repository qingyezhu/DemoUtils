package com.wangzhu;


public class OtherHelper {

    public static void main(String[] args) {

	System.out.println(OtherHelper.isPalindromeString("abab"));
	System.out.println(OtherHelper.isPalindromeString("abba"));
	System.out.println(OtherHelper.isPalindromeString("abcba"));

	// 测试结果：
	// false
	// true
	// true
    }

    /**
     * 判断字符串是否回文
     * 
     * @param str
     * @return
     */
    public static boolean isPalindromeString(String str) {
	boolean flag = true;

	if (null == str) {
	    flag = false;
	}
	for (int i = 0, length = str.length(), len = length / 2; (i < len)
		&& flag; i++) {
	    // System.out.println(i + "==========" + (length - i - 1));
	    if (str.charAt(i) != str.charAt(length - i - 1)) {
		flag = false;
	    }
	}

	return flag;
    }

}
