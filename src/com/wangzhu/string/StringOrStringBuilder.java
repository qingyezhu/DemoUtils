package com.wangzhu.string;

public class StringOrStringBuilder {
	private final static int LEN = 10000;

	public static void main(String[] args) {
		StringOrStringBuilder.testString();
		StringOrStringBuilder.testStringBuilder();
	}

	private static void testString() {
		long start = System.currentTimeMillis();
		String str = "";
		for (int i = 0; i < StringOrStringBuilder.LEN; i++) {
			str += "test";
		}
		long end = System.currentTimeMillis();
		System.out.println("testString: " + (end - start));
	}

	private static void testStringBuilder() {
		long start = System.currentTimeMillis();
		StringBuilder accum = new StringBuilder();
		for (int i = 0; i < StringOrStringBuilder.LEN; i++) {
			accum.append("test");
		}
		long end = System.currentTimeMillis();
		System.out.println("testString: " + (end - start));
	}
}
