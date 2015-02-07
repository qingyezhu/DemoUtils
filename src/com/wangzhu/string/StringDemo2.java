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
		 * 分析：在程序编译期间，JVM就将字符串常量的+连接优化为连接后的值。即在编译期字符串常量的值就确定下来了。<br/>
		 */

		String str21 = "ab";
		String str22 = "b";
		String str23 = "a" + str22;
		System.out.println(str21 == str23);// false
		/**
		 * 分析：JVM对于字符串引用，由于在字符串的+连接中，有字符串引用的存在，故引用的值在程序编译期无法确定，<br/>
		 * 只有在程序运行期来动态分配并将连接后的新地址赋给变量。
		 */

		String str31 = "ab";
		final String str32 = "b";
		String str33 = "a" + str32;
		System.out.println(str31 == str33);// true
		/**
		 * 分析：对于final修饰的变量，其在编译时被解析为常量值的一个本地拷贝存储到自己的常量池中或嵌入到其字节码流中。<br/>
		 */

		System.out.println(str21 == str23.intern());// true
		/**
		 * 分析：使用String的intern方法，会返回该字符串在常量池中的地址值。<br/>
		 */
	}

}
